package emlakcepte.model;

import java.time.LocalDateTime;

public class Realty {

	private Long no;
	private String title;
	private LocalDateTime publishedDate;
	private User user;
	private RealtyType realtyStatus;
	private String province;
	private String district;
	private EstateType estateType;
	private Status rentalStatus;

	public Realty() {
		super();
	}

	public Realty(Long no, String title, LocalDateTime publishedDate, User user, RealtyType realtyStatus) {
		super();
		this.no = no;
		this.title = title;
		this.publishedDate = publishedDate;
		this.user = user;
		this.realtyStatus = realtyStatus;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDateTime publishedDate) {
		this.publishedDate = publishedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RealtyType getRealtyStatus() {
		return realtyStatus;
	}

	public void setRealtyStatus(RealtyType status) {
		this.realtyStatus = status;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public EstateType getEstateType() {
		return estateType;
	}

	public void setEstateType(EstateType estateType) {
		this.estateType = estateType;
	}

	public Status getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(Status rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	@Override
	public String toString() {
		return "Realty [no=" + no + ", title=" + title + ", publishedDate=" + publishedDate + ", user=" + user
				+ ", status=" + getRealtyStatus() + ", province=" + province + "]";
	}
}
