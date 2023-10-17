package com.shopme.common.entity.product;



import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.shopme.common.entity.Brand;
import com.shopme.common.entity.Category;
import com.shopme.common.entity.IdBaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="products")
public class Product extends IdBaseEntity{
	
	@Column(unique = true, length = 256, nullable = false)
	private String name;
	
	@Column(unique = true, length = 256, nullable = false)
	private String alias;
	
	@Column( length = 512, nullable = false, name = "short_description")
	private String shortDescription;
	
	@Column(length = 4096, nullable = false, name = "full_description")
	private String fullDescription;
	
	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name="updated_time")
	private Date updatedTime;
	
	private boolean enabled;
	
	@Column(name="in_stock")
	private boolean inStock;

	private float cost;
	
	private float price;
	
	@Column(name="discount_percent")
	private float discountPercent;
	
	private float length;
	private float width;
	private float height;
	private float weight;
	
	@Column(name="main_image", nullable=false)
	private String mainImage;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductImage> images = new HashSet<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductDetail> details = new HashSet<>();
	
	private int reviewCount;
	private float averageRating;
	
	@Transient 
	private boolean customerCanReview;
	
	@Transient 
	private boolean reviewedByCustomer;

	
	public boolean isCustomerCanReview() {
		return customerCanReview;
	}

	public void setCustomerCanReview(boolean customerCanReview) {
		this.customerCanReview = customerCanReview;
	}

	public boolean isReviewedByCustomer() {
		return reviewedByCustomer;
	}

	public void setReviewedByCustomer(boolean reviewedByCustomer) {
		this.reviewedByCustomer = reviewedByCustomer;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}

	public Product() {
	}

	public Product(String name) {
		this.name = name;
	}



	public Product(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getFullDescription() {
		return fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date date) {
		this.createdTime = (Date) date;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date date) {
		this.updatedTime = (Date) date;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public Set<ProductImage> getImages() {
		return images;
	}
	public Set<ProductDetail> getDetails() {
		return details;
	}

	public void setImages(Set<ProductImage> images) {
		this.images = images;
	}
	
	public void addExtraImage(String imageName) {
		this.images.add(new ProductImage(imageName, this));
	}
	/*
	 * @Transient public String getMainImagePath() { if(id == null || mainImage ==
	 * null) return "/images/image-thumbnail.png";
	 * 
	 * return "/product-images/" + this.id + "/" + this.mainImage; }
	 */
	
	  @Transient 
	  public String getMainImagePath() {

		  if(id == null || mainImage == null ) {
			  return  "/images/image-thumbnail.png";
		  }
	  return "/product-images/" + this.id + "/" + this.mainImage;
	  }
	 
	/*
	 * @Transient public String getMainImagePath() { if(id == null || mainImage ==
	 * null) return "/images/image-thumbnail.png";
	 * 
	 * String myappHome = System.getenv("ShopmeWebParent"); String imagePath =
	 * myappHome + "/product-images/" + this.id + "/" + this.mainImage;
	 * 
	 * File imageFile = new File(imagePath); if(!imageFile.exists()) { return
	 * "/images/image-thumbnail.png"; }
	 * 
	 * 
	 * if(!imageFile.canRead()) { return "/images/image-thumbnail.png"; }
	 * 
	 * return imagePath; }
	 */
	
	public void setDetails(Set<ProductDetail> details) {
		this.details = details;
	}

	public void addDetail(String name, String value) {
		this.details.add(new ProductDetail (name,value,this) );
		
	}
	public void addDetail(Integer id,String name, String value) {
		this.details.add(new ProductDetail (id,name,value,this) );
		
	}

	public boolean containsImageName(String imageName) {
		Iterator<ProductImage> iterator = images.iterator();
	
		while(iterator.hasNext()) {
			ProductImage image = iterator.next();
			if(image.getName().equals(imageName)) {
				return true;
			}
		}
		return false;
	}

	@Transient
	public String getShortName() {
		if(name.length() > 50) {
			return name.substring(0,50).concat("...");
		}
		return name;
	}
	
	@Transient
	public float getDiscountPrice() {
		if(discountPercent > 0) {
			return price * ((100-discountPercent)/100);
		}
		return this.price;
	}
	
	@Transient
	public String getURI() {
		return "/p/" + this.alias;
	}
	
	public void copyStateFrom(Product otherProduct) {
 //       this.name = otherProduct.getName();
        this.alias = otherProduct.getAlias();
        this.shortDescription = otherProduct.getShortDescription();
        this.fullDescription = otherProduct.getFullDescription();
//        this.createdTime = otherProduct.getCreatedTime();
//        this.updatedTime = otherProduct.getUpdatedTime();
        this.enabled = otherProduct.isEnabled();
        this.inStock = otherProduct.isInStock();
        this.cost = otherProduct.getCost();
        this.price = otherProduct.getPrice();
        this.discountPercent = otherProduct.getDiscountPercent();
        this.length = otherProduct.getLength();
        this.width = otherProduct.getWidth();
        this.height = otherProduct.getHeight();
        this.weight = otherProduct.getWeight();
        this.mainImage = otherProduct.getMainImage();
    }

}
