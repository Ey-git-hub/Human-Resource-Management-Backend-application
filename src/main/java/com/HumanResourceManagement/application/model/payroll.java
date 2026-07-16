import jakarta.persistence.Entity;
import lombok.Data;
@Data
@Entity

public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "pay_period_start", nullable = false)
    private LocalDate payPeriodStart;

    @Column(name = "pay_period_end", nullable = false)
    private LocalDate payPeriodEnd;

    @Column(nullable = false)
    private Double basicSalary;

    private Double allowances = 0.0;

    private Double deductions = 0.0;

    @Column(nullable = false)
    private Double netSalary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayrollStatus status = PayrollStatus.PENDING;

    private LocalDate paymentDate;

    @Column(updatable = false)
    private LocalDateTime createdAt;
}