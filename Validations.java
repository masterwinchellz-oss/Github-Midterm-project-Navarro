import java.util.Arrays;
import java.util.List;


public class Validations {

    public static final List<String> Valid_Categories = Arrays.asList("Clothing", 
        "Electronic", "Entertainment" );

        private Validations() {
        }

        public static boolean isBlank(String value) {
            return value == null || value.trim().isEmpty();
        }

        public static boolean isValidCategory(String category) {
            if (isBlank(category)) {
                return false;
            }
            for (String valid : Valid_Categories) {
                if (valid.equalsIgnoreCase(category.trim())) {
                    return true;
                }
            }
            return false;
        }

        public static String normalCategory(String category) {
            for (String valid : Valid_Categories) {
                if (valid.equalsIgnoreCase(category.trim())) {
                    return valid;
                }
            }
            return category;
        }

        public static boolean isValidInteger(String value) {
            if (isBlank(value)) {
                return false;
            }
            try {
                Integer.parseInt(value.trim());
                return true;

            } catch (NumberFormatException e) {
                return false;
            }
        }

        public static boolean isValidDouble(String value) {
            if (isBlank(value)) {
                return false;
            }
            try {
                Double.parseDouble(value.trim());
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        public static boolean isValidQuantity(String value) {
            return isValidInteger(value) && Integer.parseInt(value.trim()) >= 0;
        }

        public static boolean isValidPrice(String value) {
            return isValidDouble(value) && Double.parseDouble(value.trim()) > 0;
        }

        public static boolean isValidId(String id){
            return !isBlank(id) && !id.trim().contains(" ");
        }

        public static boolean isValidName(String name) {
            return !isBlank(name);
        }


        public static boolean isValidMenuChoice(String value, int min, int max) {
            if (!isValidInteger(value)) {
                return false;
            }
            int choice = Integer.parseInt(value.trim());
            return choice >= min && choice <= max;
        }

        public static boolean isValidYes_No(String value, String option_A, String option_B) {
            if (isBlank(value)) {
                return false;
            }
            String trimmed = value.trim();
            return trimmed.equalsIgnoreCase(option_A) || trimmed.equalsIgnoreCase(option_B);
        }


    
}
