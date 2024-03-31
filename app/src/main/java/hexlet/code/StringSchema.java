package hexlet.code;

public class StringSchema {
    private String subStr;
    private boolean isRequired = false;
    private int minLength = -1;

    public boolean isValid(String str) {
        boolean isValidTrue = true;
        if (isRequired && (str == null || str.isEmpty())) {
            isValidTrue = false;
        }
        if (isValidTrue && minLength != -1) {
            isValidTrue = str.length() > minLength;
        }
        if (isValidTrue && (subStr != null && !subStr.isEmpty())) {
            isValidTrue = str.contains(subStr);
        }
        return isValidTrue;
    }

    public StringSchema minLength(int minLengthIn) {
        this.minLength = Math.abs(minLengthIn);
        return this;
    }

    public StringSchema contains(String subStrIn) {
        this.subStr = subStrIn;
        return this;
    }

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }
}
