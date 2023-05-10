package bg.softuni.pizzashop.service;

public interface IpBlackListService {
    public boolean isBlackListed(String ipAddress);
}
