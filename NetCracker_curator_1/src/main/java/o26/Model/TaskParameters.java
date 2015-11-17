package o26.Model;

public enum TaskParameters {
    NAME , DESCRIPTION , DATE, CONTACTS;

    /*
    * Это проверка на синтаксис.
    * Мы не проверяем дату, потому что в интерфейсе мы будем просить передавать только число.
    * Например,
    * int year = in.nextInt();
    * Если будет введено не число, то там вылезет исключение.
    * */

    public boolean validate(String value) {
        return value.length() != 0;
    }
}