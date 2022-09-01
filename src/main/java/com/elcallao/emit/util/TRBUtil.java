package com.elcallao.emit.util;

public class TRBUtil {
    public enum CardStatus {
        ACTIVATED( "A"),
        BLOCKED("B"),
        SUSPENDED("T"),
        RISK("R"),
        STOLEN("S"),
        LOST( "L"),
        Lost("D"),
        EXPIRED("E"),
        FRAUD("F"),
        DEACTIVATED("N");
        private String value;
        CardStatus(String value) {
        this.value = value;
        }
        public String getValue() {
            for (CardStatus val : CardStatus.values()){
                 if(value.equals(val)){
                     return  val.value;
                 }
            }
            return value;
        }
    }
}
