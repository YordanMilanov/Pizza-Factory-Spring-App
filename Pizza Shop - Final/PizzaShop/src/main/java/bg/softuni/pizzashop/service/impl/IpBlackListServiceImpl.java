package bg.softuni.pizzashop.service.impl;

import bg.softuni.pizzashop.repository.IpAddressRepository;
import bg.softuni.pizzashop.service.IpBlackListService;
import org.springframework.stereotype.Service;

@Service
public class IpBlackListServiceImpl implements IpBlackListService {

    private final IpAddressRepository ipAddressRepository;

    public IpBlackListServiceImpl(IpAddressRepository ipAddressRepository) {
        this.ipAddressRepository = ipAddressRepository;
    }

    public boolean isBlackListed(String ipAddress) {
        if(ipAddressRepository.findByIpAddress(ipAddress).isPresent()) {
        return ipAddressRepository.findByIpAddress(ipAddress).get().isBanned();
        }
        return false;
    }
}
