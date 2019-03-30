package GuiLayer;

import DataObject.ChiTietHoaDon;

/**
 * @author truong
 *
 *         sự kiện mua 1 sản phẩm--buy a product custom event
 */
public interface BuyEvent {
	public void onBuyProduct(ChiTietHoaDon sp);
}