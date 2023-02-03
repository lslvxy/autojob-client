package com.laysan.autojob.client.dto;

import com.laysan.autojob.client.entity.Account;
import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {
    private String type;
    private String        userId;
    private List<Account> accountList;
}
