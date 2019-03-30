package DataAccessLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import DataObject.DiaNhac;
import DataObject.DiaPhim;
import DataObject.HoaDon;
import DataObject.InterfaceHaveID;
import DataObject.KhachHang;
import DataObject.NhanVien;
import DataObject.Sach;

/**
 * @author truong
 * @param <T>
 *            lớp truy cập dữ liệu cơ bản, thao tác với file; duyệt, add,
 *            remove, insert với arraylist; dùng với lớp kế thừa interface T
 *            (chứa 2 method: getID() và setID();T bao gồm SanPham, NhanVien,
 *            KhacHang, HoaDon
 */
public class DataAccess<T extends InterfaceHaveID> {
	private Class<T> type;
	private ArrayList<T> list;
	private String fileName;

	public DataAccess(Class<T> type) {
		list = new ArrayList<>();
		this.type = type;

		// kiểm tra T là class nào
		if (this.type.equals(Sach.class)) {
			fileName = "data/sach.dat";
		} else if (this.type.equals(DiaNhac.class)) {
			fileName = "data/diaNhac.dat";
		} else if (this.type.equals(DiaPhim.class)) {
			fileName = "data/diaPhim.dat";
		} else if (this.type.equals(NhanVien.class)) {
			fileName = "data/nhanVien.dat";
		} else if (this.type.equals(KhachHang.class)) {
			fileName = "data/khachHang.dat";
		} else if (this.type.equals(HoaDon.class)) {
			fileName = "data/hoaDon.dat";
		}
	}

	/**
	 * @param object
	 * 
	 *            thêm object vào list, nếu như không trùng id
	 */
	public boolean insert(T object) {
		if (!checkByID(object.getID())) {
			list.add(object);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param object
	 * @param index
	 * 
	 *            kiểm tra index có < length không? nếu có: chèn object vào vị
	 *            trí index
	 */
	public boolean update(T object, int index) {
		if (index <= list.size()) {
			list.set(index, object);
			return true;
		} else
			return false;
	}

	/**
	 * @param object
	 * @param id
	 * 
	 *            kiểm tra id có tồn tại hay không; nếu có, thay thế nó
	 */
	public boolean update(T object, String id) {
		for (T temp : list) {
			if (temp.getID().equals(id)) {
				int index = list.indexOf(temp);
				list.set(index, object);
				return true;
			}
		}
		return false;
	}

	/**
	 * @param id
	 * 
	 *            kiểm tra id có tồn tại hay không, nếu có: xóa object đó
	 */
	public boolean delete(String id) {
		for (T temp : list) {
			if (temp.getID().equals(id)) {
				list.remove(temp);
				return true;
			}
		}
		return false;
	}

	public ArrayList<T> getList() {
		return list;
	}

	/**
	 * @param id
	 * 
	 *            kiểm tra xem có object nào có mã là id không
	 */
	public boolean checkByID(String id) {
		for (T temp : list) {
			if (temp.getID().equals(id)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param id
	 * @return object
	 * 
	 *         lấy object có mã là id
	 */
	public T getByID(String id) {
		for (T temp : list) {
			if (temp.getID().equals(id)) {
				return temp;
			}
		}
		return null;
	}

	public void setList(ArrayList<T> list) {
		this.list = list;
	}

	/**
	 * @param id
	 * @return chỉ số
	 * 
	 *         lấy chỉ số của object có mã là id
	 */
	public int getNumberByID(String id) {
		for (T temp : list) {
			if (temp.getID().equals(id)) {
				return list.indexOf(temp);
			}
		}
		return -1;
	}

	/**
	 * ghi arraylist ra file
	 */
	public void writeFile() {
		try {
			File file = new File(fileName);
			file.createNewFile();
			FileOutputStream f = new FileOutputStream(file);
			ObjectOutputStream oStream = new ObjectOutputStream(f);
			oStream.writeObject(list);
			oStream.close();
			System.out.println("Write success to " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("lỗi ghi file");
		}
	}

	/**
	 * đọc arraylist từ file
	 */
	public void readFile() {
		try {
			FileInputStream f = new FileInputStream(fileName);
			ObjectInputStream inStream = new ObjectInputStream(f);
			ArrayList<T> readObject = (ArrayList<T>) inStream.readObject();
			list = readObject;
			inStream.close();
			f.close();
		} catch (IOException e) {
			System.out.println("lỗi đọc file" + fileName);
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println("lỗi classnotfound " + fileName);
			e1.printStackTrace();
		}
	}
}
