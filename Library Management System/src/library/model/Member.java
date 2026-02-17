package library.model;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private String phone;
    private String address;
    
    public Member(String memberId, String name, String email,
                 String phone, String address) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    // Getters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    
    @Override
    public String toString() {
        return String.format("[%s] %s | Email: %s | Phone: %s", 
            memberId, name, email, phone);
    }
}
