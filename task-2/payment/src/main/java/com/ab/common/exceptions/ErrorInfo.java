package com.ab.common.exceptions;

import com.ab.common.Lang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {

    private int code;

    private Map<Lang, String> messages;

}
