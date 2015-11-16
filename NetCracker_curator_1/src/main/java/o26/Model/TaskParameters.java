package o26.Model;

public enum TaskParameters {
    NAME {
        public boolean validate(String name) { //Валидирует синтаксис.
            if (name.length() != 0) {
                return true;
            } else {
                return false;
            }
        }
    }, DESCRIPTION {
        public boolean validate(String description) {
            if (description.length() != 0) {
                return true;
            } else {
                return false;
            }
        }
    }, DATE {
        public boolean validate() {
            return true;
        }
    }, CONTACTS {
        public boolean validate(String contacts) {
            if (contacts.length() != 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}