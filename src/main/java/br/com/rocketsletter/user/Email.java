package br.com.rocketsletter.user;

public class Email {

    private final String address;

    public Email(String address) throws InvalidEmailException {

        if(address.matches("^([a-z0-9]+(?:[._-][a-z0-9]+)*)@([a-z0-9]+(?:[.-][a-z0-9]+)*\\.[a-z]{2,})$")) {
            this.address = address;
            return;
        }

        throw new InvalidEmailException();
    }

    public String getAddress() {
        return address;
    }
}
