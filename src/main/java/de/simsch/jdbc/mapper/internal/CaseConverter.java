package de.simsch.jdbc.mapper.internal;

public final class CaseConverter {

    private CaseConverter() { }

    public static String toLowerUnderscore(String camelCase) {
        if (camelCase == null) {
            throw new NullPointerException("Case converter can not handle 'null' values!");
        }
        StringBuilder result = new StringBuilder();
        if (camelCase.toUpperCase().equals(camelCase)) {
            return camelCase.toLowerCase();
        }
        char[] chars = camelCase.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i]) && i > 0) {
                result.append("_");
            }
            result.append(Character.toLowerCase(chars[i]));
        }
        return result.toString();
    }
}
