Tại sao @Before chỉ in log không ngăn chặn phương thức gốc thực thi?

Giải thích ngắn:

1) @Before Advice chạy trước phương thức mục tiêu nhưng không kiểm soát hoặc thay đổi việc tiếp tục thực thi phương thức đó. Nếu trong advice chỉ thực hiện hành động không làm ném ngoại lệ (ví dụ: gọi System.out.println), luồng thực thi sẽ tiếp tục bình thường và Spring sẽ gọi phương thức gốc sau khi advice hoàn tất.

2) Để ngắt ngay luồng và ngăn phương thức gốc không được gọi, advice phải ngăn việc tiếp tục — bằng cách ném một ngoại lệ (throw) trong @Before, hoặc dùng @Around advice và không gọi Proceed (proceed()) khi muốn chặn.

3) Vì vậy, chỉ in log là không đủ để thực hiện RBAC (role-based access control). Cần ném một RuntimeException (hoặc AccessDeniedException) để dừng luồng và báo lỗi cho caller.

Ví dụ cách đúng:
- Dùng @Before: nếu phát hiện không có quyền -> throw new RuntimeException("Access denied").
- Hoặc dùng @Around: kiểm tra quyền và chỉ gọi proceedingJoinPoint.proceed() khi được phép; nếu không thì trả về lỗi hoặc ném ngoại lệ.

Kết luận: Advice cần gây ra ngoại lệ hoặc ngăn gọi proceedingJoinPoint.proceed() để thực sự chặn hành động; việc chỉ in ra thông báo không thay đổi luồng điều khiển và không đủ để bảo vệ business logic.

