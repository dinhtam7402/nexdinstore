package com.nexdin.nexdinstore.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nexdin.nexdinstore.domain.enums.EVoucher;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vouchers")
public class Vouchers {
    @Id
    private String voucherID;
    private String code;
    private String description;
    private EVoucher voucherType;
    private int voucherValue;
    private int minOrderValue;
    private int maxValueAmount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime endDate;
    private int usageLimit;
    private int usedCount;

    @OneToMany(mappedBy = "voucher")
    private List<Orders> orders;
}
