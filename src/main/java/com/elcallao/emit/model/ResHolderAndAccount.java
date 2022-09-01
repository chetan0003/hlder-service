package com.elcallao.emit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResHolderAndAccount {
    private String  account_id;
    private String holder_id;
    private String  currency_ison;
    private String available_balance;
    private String settled_balance;
    private String reserved_balance;
    private String account_status;
    private String status_change_reason_code;
    private String status_change_note;
    private String date_updated;
    private String program_id;
    private String fee_group_id;
    private String limit_group_id;
    private String risk_rules_group_id;
}
