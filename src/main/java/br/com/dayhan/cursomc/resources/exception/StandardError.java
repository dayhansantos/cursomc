package br.com.dayhan.cursomc.resources.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class StandardError implements Serializable {

    private static final long serialVersionUID = -1749763846091223076L;

    private Integer status;
    private String msg;
    private Long timeStamp;

    public StandardError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }
}
