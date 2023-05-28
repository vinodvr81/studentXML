package studentXMl;


class Student {
    private String id;
    private String name;
    private String std;
    private String gender;
    private String grade;
    private String address;

    public Student(String id, String name, String std, String gender, String grade, String address) {
        this.id = id;
        this.name = name;
        this.std = std;
        this.gender = gender;
        this.grade = grade;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStd() {
        return std;
    }

    public String getGender() {
        return gender;
    }

    public String getgrade() {
        return grade;
    }

    public String getAddress() {
        return address;
    }
}

