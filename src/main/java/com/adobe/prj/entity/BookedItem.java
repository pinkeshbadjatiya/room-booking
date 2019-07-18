package com.adobe.prj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "booked_items")
public class BookedItem {

	// composite primary key
	@EmbeddedId
	private BookedItemId id;
	
	@Column(name = "booked_qty")
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Integer> booked_qty = new ArrayList<Integer>();
	
	@Embeddable
	public class BookedItemId implements Serializable {
		int item_id;
		
		String item_type;
		
		@Temporal(TemporalType.DATE)
		Date date;
		
		public int getItem_id() {
			return item_id;
		}

		public void setItem_id(int item_id) {
			this.item_id = item_id;
		}

		public String getItem_type() {
			return item_type;
		}

		public void setItem_type(String item_type) {
			this.item_type = item_type;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public BookedItemId() {
			super();
		}
		
	}

	public BookedItemId getId() {
		return id;
	}

	public void setId(BookedItemId id) {
		this.id = id;
	}

	public List<Integer> getBooked_qty() {
		return booked_qty;
	}

	public void setBooked_qty(List<Integer> booked_qty) {
		this.booked_qty = booked_qty;
	}
	
	

}
