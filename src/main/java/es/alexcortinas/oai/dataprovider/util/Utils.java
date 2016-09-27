package es.alexcortinas.oai.dataprovider.util;

public class Utils {

    public static String removeTags(String str) {
        String pattern = "[<][^>]*[>]";

        if (str == null) {
            return null;
        }

        return str.replaceAll(pattern, "");
    }

    public static String getArrayItem(String[] array, int index) {
        if (array == null) {
            return null;
        }
        if (array.length > index) {
            return array[index];
        }
        return null;
    }

    public static String enumToCapitalizedString(Enum<?> val, boolean firstCapital) {

        if (val == null) {
            return null;
        }

        String aux = StringUtils.capitalizeFully(val.name(), new char[] { '_' }).replaceAll("_", "");

        if (!firstCapital) {
            return aux.substring(0, 1).toLowerCase() + aux.substring(1);
        }

        return aux;
    }
}
