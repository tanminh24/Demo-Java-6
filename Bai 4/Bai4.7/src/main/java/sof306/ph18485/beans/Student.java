package sof306.ph18485.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String email;
    private String fullname;
    private Double marks;
    private Boolean gender;
    private String country;
    
    @JsonIgnore
    public Object[] getArray() {
        return new Object[] {
            this.email,
            this.fullname,
            this.marks,
            this.gender,
            this.country
        };
    }
}
