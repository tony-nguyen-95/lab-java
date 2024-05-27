package data;

import java.util.HashMap;
import java.util.Map;

public class MapData {
    private static MapData instance;
    private Map<String, String> codeProvinceMap;
    private Map<Integer, String> sexCenturyCodeFirstStringMap;

    // Prevent external instantiation, just one instance
    private MapData() {
        this.codeProvinceMap = new HashMap<>();
        setMapProvince(codeProvinceMap);

        this.sexCenturyCodeFirstStringMap = new HashMap<>();
        setCenteryCodeMap(sexCenturyCodeFirstStringMap);
    }

    public Map<String, String> getCodeProvinceMap() {
        return codeProvinceMap;
    }

    public Map<Integer, String> getSexCenturyCodeFirstStringMap() {
        return sexCenturyCodeFirstStringMap;
    }

    public static MapData getInstance() {
        if (instance == null) {
            instance = new MapData();
        }
        return instance;
    }

    private void setMapProvince(Map<String, String> codeProvinceMap) {
        codeProvinceMap.put("001", "Hà Nội");
        codeProvinceMap.put("002", "Hà Giang");
        codeProvinceMap.put("004", "Cao Bằng");
        codeProvinceMap.put("006", "Bắc Kạn");
        codeProvinceMap.put("008", "Tuyên Quang");
        codeProvinceMap.put("010", "Lào Cai");
        codeProvinceMap.put("011", "Điện Biên");
        codeProvinceMap.put("012", "Lai Châu");
        codeProvinceMap.put("014", "Sơn La");
        codeProvinceMap.put("015", "Yên Bái");
        codeProvinceMap.put("017", "Hòa Bình");
        codeProvinceMap.put("019", "Thái Nguyên");
        codeProvinceMap.put("020", "Lạng Sơn");
        codeProvinceMap.put("022", "Quảng Ninh");
        codeProvinceMap.put("024", "Bắc Giang");
        codeProvinceMap.put("025", "Phú Thọ");
        codeProvinceMap.put("026", "Vĩnh Phúc");
        codeProvinceMap.put("027", "Bắc Ninh");
        codeProvinceMap.put("030", "Hải Dương");
        codeProvinceMap.put("031", "Hải Phòng");
        codeProvinceMap.put("033", "Hưng Yên");
        codeProvinceMap.put("034", "Thái Bình");
        codeProvinceMap.put("035", "Hà Nam");
        codeProvinceMap.put("036", "Nam Định");
        codeProvinceMap.put("037", "Ninh Bình");
        codeProvinceMap.put("038", "Thanh Hóa");
        codeProvinceMap.put("040", "Nghệ An");
        codeProvinceMap.put("042", "Hà Tĩnh");
        codeProvinceMap.put("044", "Quảng Bình");
        codeProvinceMap.put("045", "Quảng Trị");
        codeProvinceMap.put("046", "Thừa Thiên Huế");
        codeProvinceMap.put("048", "Đà Nẵng");
        codeProvinceMap.put("049", "Quảng Nam");
        codeProvinceMap.put("051", "Quảng Ngãi");
        codeProvinceMap.put("052", "Bình Định");
        codeProvinceMap.put("054", "Phú Yên");
        codeProvinceMap.put("056", "Khánh Hòa");
        codeProvinceMap.put("058", "Ninh Thuận");
        codeProvinceMap.put("060", "Bình Thuận");
        codeProvinceMap.put("062", "Kon Tum");
        codeProvinceMap.put("064", "Gia Lai");
        codeProvinceMap.put("066", "Đắk Lắk");
        codeProvinceMap.put("067", "Đắk Nông");
        codeProvinceMap.put("068", "Lâm Đồng");
        codeProvinceMap.put("070", "Bình Phước");
        codeProvinceMap.put("072", "Tây Ninh");
        codeProvinceMap.put("074", "Bình Dương");
        codeProvinceMap.put("075", "Đồng Nai");
        codeProvinceMap.put("077", "Bà Rịa - Vũng Tàu");
        codeProvinceMap.put("079", "Hồ Chí Minh");
        codeProvinceMap.put("080", "Long An");
        codeProvinceMap.put("082", "Tiền Giang");
        codeProvinceMap.put("083", "Bến Tre");
        codeProvinceMap.put("084", "Trà Vinh");
        codeProvinceMap.put("086", "Vĩnh Long");
        codeProvinceMap.put("087", "Đồng Tháp");
        codeProvinceMap.put("089", "An Giang");
        codeProvinceMap.put("091", "Kiên Giang");
        codeProvinceMap.put("092", "Cần Thơ");
        codeProvinceMap.put("093", "Hậu Giang");
        codeProvinceMap.put("094", "Sóc Trăng");
        codeProvinceMap.put("095", "Bạc Liêu");
        codeProvinceMap.put("096", "Cà Mau");
    }

    private void setCenteryCodeMap(Map<Integer, String> sexCenturyCodeFirstStringMap) {
        sexCenturyCodeFirstStringMap.put(0, "Nam | 19");
        sexCenturyCodeFirstStringMap.put(1, "Nữ | 19");
        sexCenturyCodeFirstStringMap.put(2, "Nam | 20");
        sexCenturyCodeFirstStringMap.put(3, "Nữ | 20");
        sexCenturyCodeFirstStringMap.put(4, "Nam | 21");
        sexCenturyCodeFirstStringMap.put(5, "Nữ | 21");
        sexCenturyCodeFirstStringMap.put(6, "Nam | 22");
        sexCenturyCodeFirstStringMap.put(7, "Nữ | 22");
        sexCenturyCodeFirstStringMap.put(8, "Nam | 23");
        sexCenturyCodeFirstStringMap.put(9, "Nữ | 23");
    }

}