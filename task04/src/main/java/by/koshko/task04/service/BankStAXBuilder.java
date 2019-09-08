package by.koshko.task04.service;

import by.koshko.task04.entity.Bank;
import by.koshko.task04.entity.Banks;
import by.koshko.task04.entity.Currency;
import by.koshko.task04.entity.Deposit;
import by.koshko.task04.entity.Depositor;
import by.koshko.task04.entity.ElementType;
import by.koshko.task04.entity.SavingDeposit;
import by.koshko.task04.entity.SettlementDeposit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Optional;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;

public final class BankStAXBuilder implements BanksBuilder {
    private Logger logger = LogManager.getLogger(getClass());
    private InputStream in;
    private XMLStreamReader reader;
    private Bank currentBank;
    private Depositor currentDepositor;
    private Deposit currentDeposit;
    private ElementType type;
    private Banks banks = new Banks();

    @Override
    public Banks buildBanks(final String xmlFilePath)
            throws BankBuilderException {
        logger.info("Path to xml file: '{}'", xmlFilePath);
        logger.info("Configuring StAX builder...");
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new BankBuilderException("Path to xml file is empty.");
        }
        try {
            in = new FileInputStream(new File(xmlFilePath));
            XMLInputFactory factory = XMLInputFactory.newDefaultFactory();
            reader = factory.createXMLStreamReader(in);
            logger.info("Configuring StAX builder complete");
            logger.info("Start parsing xml file: '{}'", xmlFilePath);
            int state;
            while (reader.hasNext()) {
                state = reader.next();
                switch (state) {
                    case XMLStreamConstants.START_ELEMENT:
                        logger.debug("START_ELEMENT");
                        startElement(reader);
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        logger.debug("END_ELEMENT");
                        endElement(reader.getLocalName());
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new BankBuilderException(String.format("File '%s' not found",
                    xmlFilePath));
        } catch (XMLStreamException e) {
            throw new BankBuilderException("SAXParser configuration error.", e);
        }
        logger.debug("Parsing process is finished.");
        return banks;
    }

    private void startElement(final XMLStreamReader doc)
            throws XMLStreamException {
        type = ElementType.fromValue(doc.getLocalName());
        logger.debug("element: {}", doc.getLocalName());
        if (type == null) {
            return;
        }
        switch (type) {
            case BANK:
                handleBank(doc);
                break;
            case DEPOSIT:
                handleDeposit(doc);
                break;
            case DEPOSITOR:
                currentDepositor = new Depositor();
                currentDeposit.setDepositor(currentDepositor);
                break;
            case NAME:
                currentBank.setName(getXmlText(reader));
                break;
            case TYPE:
                currentDeposit.setType(getXmlText(reader));
                break;
            case IBAN:
                currentDeposit.setIban(getXmlText(reader));
                break;
            case FIRSTNAME:
                currentDepositor.setFirstName(getXmlText(reader));
                break;
            case MIDDLENAME:
                currentDepositor.setMiddleName(getXmlText(reader));
                break;
            case LASTNAME:
                currentDepositor.setLastName(getXmlText(reader));
                break;
            case IDENTIFICATION:
                currentDepositor.setIdentification(getXmlText(reader));
                break;
            case DEPOSITDATE:
                currentDeposit
                        .setDepositDate(LocalDate.parse(getXmlText(reader)));
                break;
            case AMOUNT:
                currentDeposit.setAmount(parseDouble(getXmlText(reader)));
                break;
            case CURRENCY:
                currentDeposit
                        .setCurrency(Currency.fromValue(getXmlText(reader)));
                break;
            case PROFITABILITY:
                currentDeposit
                        .setProfitability(parseDouble(getXmlText(reader)));
                break;
            case TERM:
                currentDeposit.setTerm(getXmlText(reader));
                break;
            case WITHDRAWAL:
                currentDeposit.setWithdrawal(parseBoolean(getXmlText(reader)));
                break;
            case REFILL:
                currentDeposit.setRefill(parseBoolean(getXmlText(reader)));
                break;
            case CAPITALIZATION:
                currentDeposit
                        .setCapitalization(parseBoolean(getXmlText(reader)));
                break;
            case PAYOUT:
                ((SavingDeposit) currentDeposit).setPayout(getXmlText(reader));
                break;
            case MINBALANCE:
                ((SettlementDeposit) currentDeposit)
                        .setMinBalance(parseDouble(getXmlText(reader)));
                break;
            default:
                break;
        }
    }

    private void endElement(final String localName) {
        if (localName.equalsIgnoreCase("bank")) {
            banks.addBank(currentBank);
        }
        if (localName.equalsIgnoreCase("deposit")) {
            currentBank.addDeposit(currentDeposit);
        }
    }

    /**
     * Handles the {@code bank} element.
     *
     * @param doc attributes from the element.
     */
    private void handleBank(final XMLStreamReader doc) {
        currentBank = new Bank();
        for (int i = 0; i < doc.getAttributeCount(); i++) {
            switch (doc.getAttributeLocalName(i)) {
                case "country":
                    logger.debug("country attribute value: {}",
                            doc.getAttributeValue(i));
                    currentBank.setCountry(doc.getAttributeValue(i));
                    break;
                case "site":
                    logger.debug("site attribute value: {}",
                            doc.getAttributeValue(i));
                    currentBank.setSite(doc.getAttributeValue(i));
                    break;
                case "licence":
                    logger.debug("licence attribute value: {}",
                            doc.getAttributeValue(i));
                    currentBank
                            .setLicence(parseBoolean(doc.getAttributeValue(i)));
                    break;
                default:
                    logger.debug("Unknown attribute {}",
                            doc.getAttributeValue(i));
                    break;
            }
        }
    }

    /**
     * Handles the {@code deposit} element.
     *
     * @param doc attributes from the element.
     */
    private void handleDeposit(final XMLStreamReader doc) {
        Optional<String> type0
                = Optional.ofNullable(doc.getAttributeValue(
                        XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
                "type"));
        String id0 = doc.getAttributeValue("", "ID");
        logger.debug("ID value: {}", id0);
        switch (type0.orElse("")) {
            case "savingDeposit":
                currentDeposit = new SavingDeposit();
                currentDeposit.setId(id0);
                break;
            case "settlementDeposit":
                currentDeposit = new SettlementDeposit();
                currentDeposit.setId(id0);
                break;
            default:
                currentDeposit = new Deposit();
                currentDeposit.setId(id0);
                break;
        }
        currentDeposit.setId(id0);
    }

    private String getXmlText(final XMLStreamReader doc)
            throws XMLStreamException {
        if (doc.hasNext()) {
            doc.next();
            return doc.getText();
        }
        return "";
    }
}
