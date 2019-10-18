package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.bean.RawData;
import by.koshko.cyberwikia.dao.DaoException;
import by.koshko.cyberwikia.dao.FileManager;
import by.koshko.cyberwikia.service.ImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ImageServiceImpl implements ImageService {
    private Logger logger = LogManager.getLogger(ImageServiceImpl.class);
    private static final String UPLOAD_DIRECTORY = "images/upload";

    public String save(final RawData rawData) {
        if (rawData == null) {
            return null;
        }
        String permittedContentType = "image/";
        String contentType = rawData.getContentType();
        if (!contentType.contains(permittedContentType)) {
            logger.warn("Attempt to load file with prohibited content type");
            return null;
        }
        String container = contentType.substring(permittedContentType.length());
        try {
            byte[] data = rawData.getIn().readAllBytes();
            logger.debug("Read {} bytes of data.", data.length);
            return FileManager.save(data, UPLOAD_DIRECTORY, container);
        } catch (IOException e) {
            logger.error("Cannot save image. {}", e.getMessage());
            return null;
        }
    }

    public boolean delete(final String relativePath) {
        if (relativePath == null || relativePath.isBlank()) {
            logger.debug("Cannot delete file, path is null.");
            return false;
        }
        logger.debug("Attempt to delete file: {}", relativePath);
        try {
            FileManager.delete(relativePath);
        } catch (DaoException e) {
            logger.error("Cannot delete file. {}", e.getMessage());
            return false;
        }
        return true;
    }
}
