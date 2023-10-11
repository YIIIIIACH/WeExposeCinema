package struct;

import java.util.List;

import bean.BookingBean;
import bean.CinemaBean;
import bean.MovieBean;
import bean.OrdersBean;
import bean.ProductBean;
import bean.ProductServiceBean;
import bean.SeatBean;
import bean.ShowingBean;
import bean.ShowingTypeBean;
import bean.TheaterBean;

public class MemberOrderInfo {
	public OrdersBean order;
	public MovieBean movie;
	public TheaterBean theater;
	public CinemaBean cinema;
	public ShowingBean showing;
	public ShowingTypeBean showingType;
	public List<ProductServiceBean> productServices;
	public List< BookingBean> bookings;
	public List< SeatBean> seats;
	public List<ProductBean> addedProduct;
}
