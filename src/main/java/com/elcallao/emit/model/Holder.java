package com.elcallao.emit.model;

import java.io.Serializable;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author chetan d
 * @since 28 Aug 2022
 * @desc This class is hold the holder data.
 */
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "holderBuilder")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Holder extends TRBCredential implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String action; 
	private String request_reference_id;
	private Integer access_level;
	private Long holder_id;
	@Size(min = 1 , max = 7)
	private String title;
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z-,./ ]+$")
	@Size(min =1,max = 50)
	private String first_name;
	@NotEmpty()
	@Size(min =1,max = 50)
	private String last_name;
	@NotEmpty
	@Size(min =1,max = 100)
	private String address_line_1;
	@Size(min =1,max = 100)
	private String address_line_2;
	@Size(min =1,max = 100)
	private String address_line_3;
	@Size(min =1,max = 100)
	private String address_line_4;
	@Size(min =1,max = 50)
	@NotNull
	@Size(min = 1,max = 9)
	private String zip_code;
	@NotEmpty
	@Size(min =1,max = 50)
	private String city;
	private String state;
	@NotNull
	@Size(min =6,max = 15)
	private String phone_number;
	private String country_ison;
	@NotNull
	@Size(min =1,max = 20)
	private String holder_limit_group_id;
	@Email(message= "Holder email address is not valid")
	@Size(min =1,max = 50)
	private String email;
	private String date_of_birth;
	private String ip_address;
	private Integer industry_id;
	private String date_of_company_incorporation;
	private Integer kyc_completion_level;
	private String kyc_document_expiration_date;
	private Long additional_charset_id;
	private String locale;
	
	public Holder(String access_key, String access_api_id, String access_api_key, String api_version) {
		super(access_key, access_api_id, access_api_key, api_version);
	}

}
