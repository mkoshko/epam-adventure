package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.RawData;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.FileManager;
import by.koshko.cyberwikia.service.ImageService;
import by.koshko.cyberwikia.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ImageServiceImpl implements ImageService {
    private  Logger logger = LogManager.getLogger(ImageServiceImpl.class);
    private  final String defaultImageFolder = "images/upload";
    public  String save(final RawData rawData) throws ServiceException {
        if (rawData == null) {
            return null;
        }
        String content = "image/";
        String contentType = rawData.getContentType();
        if (!contentType.contains(content)) {
            throw new ServiceException("Invalid content type.");
        }
        String container = contentType.substring(content.length());
        try {
            byte[] data = rawData.getIn().readAllBytes();
            return FileManager.save(data, defaultImageFolder, container);
        } catch (IOException | DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException("Cannot save image.");
        }
    }
}
