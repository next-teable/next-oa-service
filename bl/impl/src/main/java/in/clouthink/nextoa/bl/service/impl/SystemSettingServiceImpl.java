package in.clouthink.nextoa.bl.service.impl;

import in.clouthink.nextoa.bl.exception.SystemSettingException;
import in.clouthink.nextoa.bl.model.BaseModel;
import in.clouthink.nextoa.bl.model.ShortUrl;
import in.clouthink.nextoa.bl.model.SystemSetting;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.repository.ShortUrlRepository;
import in.clouthink.nextoa.bl.repository.SystemSettingRepository;
import in.clouthink.nextoa.bl.request.SaveSystemSettingRequest;
import in.clouthink.nextoa.bl.service.SystemSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author dz
 */
@Service
public class SystemSettingServiceImpl implements SystemSettingService {

    @Autowired
    private SystemSettingRepository systemSettingRepository;

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Override
    public SystemSetting getSystemSetting() {
        return systemSettingRepository.findById(SystemSetting.DEFAULT_ID);
    }

    @Override
    public void updateSystemSetting(SaveSystemSettingRequest request, User byWho) {
        if (StringUtils.isEmpty(request.getName())) {
            throw new SystemSettingException("系统名称不能为空");
        }

        SystemSetting existedSystemSetting = getSystemSetting();
        if (existedSystemSetting == null) {
            existedSystemSetting = new SystemSetting();
            existedSystemSetting.setId(SystemSetting.DEFAULT_ID);
            BaseModel.onCreate(existedSystemSetting, byWho);
        }

        BeanUtils.copyProperties(request, existedSystemSetting);
        BaseModel.onModify(existedSystemSetting, byWho);

        systemSettingRepository.save(existedSystemSetting);
    }


    @Override
    public String getFullUrl(String shortUrlKey) {
        ShortUrl shortUrl = shortUrlRepository.findFirstByKey(shortUrlKey);
        if (shortUrl == null) {
            return null;
        }
        return shortUrl.getUrl();
    }

}
