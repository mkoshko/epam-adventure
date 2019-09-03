package by.koshko.task04.service;

import by.koshko.task04.entity.Bank;
import by.koshko.task04.entity.Banks;
import by.koshko.task04.entity.Deposit;
import by.koshko.task04.entity.Depositor;
import by.koshko.task04.entity.SavingDeposit;
import by.koshko.task04.entity.SettlementDeposit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDate;

import static by.koshko.task04.entity.Currency.fromValue;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;

/**
 * Class uses DOM JAXP for parsing xml.
 */
public final class BankDOMHandler {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger(BankDOMHandler.class);
    /**
     * List of <bank> element attributes.
     */
    private static String[] bankAttributes = {"country", "site", "licence"};
    /**
     * Xml file namespace.
     */
    private static final String NAMESPACE
            = "http://www.w3.org/2001/XMLSchema-instance";

    /**
     * Private constructor.
     */
    private BankDOMHandler() {
    }

    /**
     * Constructs {@link Banks} object from {@link Document}.
     *
     * @param document {@link Document} to be parsed.
     * @return {@link Banks} object constructed from {@link Document}.
     */
    public static Banks build(final Document document) {
        Banks banks = new Banks();
        Element root = document.getDocumentElement();
        logger.debug("Root element: {}", root.getNodeName());
        NodeList bankList = root.getElementsByTagName("bank");
        logger.debug("Number of bank elements: {}", bankList.getLength());
        var length = bankList.getLength();
        for (int i = 0; i < length; i++) {
            banks.addBank(handleBankElement((Element) bankList.item(i)));
        }
        return banks;
    }

    /**
     * Parses bank element and creates {@link Bank} objects filled with received
     * values.
     *
     * @param item bank element to parse.
     * @return {@link Bank} object.
     */
    private static Bank handleBankElement(final Element item) {
        Bank bank = new Bank();
        logger.debug("Element: {}", item.getLocalName());
        NamedNodeMap attributes = item.getAttributes();
        logger.debug("Number of attributes: {}", attributes.getLength());
        setBankAttributes(bank, attributes);
        bank.setName(getContent(item, "name"));
        logger.debug("Bank name set to: {}", bank.getName());
        NodeList depositList = item.getElementsByTagName("deposit");
        var length = depositList.getLength();
        logger.debug("Number of deposit elements: {}", length);
        for (int i = 0; i < length; i++) {
            handleDepositElement(bank, (Element) depositList.item(i));
        }
        return bank;
    }

    /**
     * Saves the attributes from bank element to the {@link Bank} object.
     *
     * @param bank       Object where attributes will be stored.
     * @param attributes Attributes to be parsed.
     */
    private static void setBankAttributes(final Bank bank,
                                          final NamedNodeMap attributes) {
        for (int i = 0; i < bankAttributes.length; i++) {
            var attr = attributes.getNamedItem(bankAttributes[i]);
            if (attr != null) {
                switch (bankAttributes[i]) {
                    case "country":
                        logger.debug("Set country: {}", attr.getNodeValue());
                        bank.setCountry(attr.getNodeValue());
                        break;
                    case "site":
                        logger.debug("Set site: {}", attr.getNodeValue());
                        bank.setSite(attr.getNodeValue());
                        break;
                    case "licence":
                        logger.debug("Set licence: {}", attr.getNodeValue());
                        bank.setLicence(parseBoolean(attr.getNodeValue()));
                        break;
                    default:
                        logger.debug("Unknown attribute:{}", bankAttributes[i]);
                }
            }
        }
    }

    /**
     * Gets the content from given element.
     *
     * @param element Element from where contented will be obtained.
     * @param name    Name of element from which content must be obtained.
     * @return string with content.
     */
    private static String getContent(final Node element, final String name) {
        return ((Element) element)
                .getElementsByTagName(name).item(0).getTextContent();
    }

    /**
     * Parses deposit element and sets the corresponding fields in
     * {@link Deposit} object.
     *
     * @param bank    Object to where {@link Deposit} object will be placed.
     * @param element Element to be parsed.
     */
    private static void handleDepositElement(final Bank bank,
                                             final Element element) {
        Depositor depositor = new Depositor();
        handleDepositorElement(depositor,
                element.getElementsByTagName("depositor").item(0));
        logger.debug("Deposit type is: {}",
                element.getAttributeNS(NAMESPACE, "type"));
        Deposit deposit;
        String depositType = element.getAttributeNS(NAMESPACE, "type");
        switch (depositType) {
            case "settlementDeposit":
                deposit = new SettlementDeposit();
                ((SettlementDeposit) deposit)
                        .setMinBalance(parseDouble(getContent(element,
                                "minBalance")));
                logger.debug("Deposit min balance set to: {}",
                        ((SettlementDeposit) deposit).getMinBalance());
                break;
            case "savingDeposit":
                deposit = new SavingDeposit();
                ((SavingDeposit) deposit).setPayout(getContent(element,
                        "payout"));
                logger.debug("Deposit min balance set to: {}",
                        ((SavingDeposit) deposit).getPayout());
                break;
            default:
                deposit = new Deposit();
                break;
        }
        deposit.setDepositor(depositor);
        deposit.setType(getContent(element, "type"));
        logger.debug("Deposit type set to: {}", deposit.getType());
        deposit.setIban(getContent(element, "iban"));
        logger.debug("Deposit iban set to: {}", deposit.getIban());
        deposit.setDepositDate(LocalDate.parse(getContent(element,
                "depositDate")));
        logger.debug("Deposit date set to: {}", deposit.getDepositDate());
        deposit.setAmount(parseDouble(getContent(element, "amount")));
        logger.debug("Deposit amount set to: {}", deposit.getAmount());
        deposit.setCurrency(fromValue(getContent(element, "currency")));
        logger.debug("Deposit currency set to: {}", deposit.getCurrency());
        deposit.setProfitability(parseDouble(getContent(element,
                "profitability")));
        logger.debug("Deposit profitability set to: {}",
                deposit.getProfitability());
        deposit.setTerm(getContent(element, "term"));
        logger.debug("Deposit term set to: {}", deposit.getTerm());
        deposit.setWithdrawal(parseBoolean(getContent(element, "withdrawal")));
        logger.debug("Deposit withdrawal set to: {}", deposit.isWithdrawal());
        deposit.setRefill(parseBoolean(getContent(element, "refill")));
        logger.debug("Deposit refill set to: {}", deposit.isRefill());
        deposit.setCapitalization(parseBoolean(getContent(element,
                "capitalization")));
        logger.debug("Deposit capitalization set to: {}",
                deposit.isCapitalization());
        deposit.setId(element.getAttribute("ID"));
        logger.debug("Deposit ID set to: {}", deposit.getId());
        bank.addDeposit(deposit);
        logger.debug("Deposit {} added to bank {}",
                deposit.getId(), bank.getName());
    }

    /**
     * Parses depositor element and set corresponding elements in
     * {@link Depositor} object.
     *
     * @param depositor Object to set up.
     * @param node      Element to be parsed.
     */
    private static void handleDepositorElement(final Depositor depositor,
                                               final Node node) {
        depositor.setFirstName(getContent(node, "firstName"));
        logger.debug("Depositor first name set to: {}",
                depositor.getFirstName());
        depositor.setMiddleName(getContent(node, "middleName"));
        logger.debug("Depositor middle name set to: {}",
                depositor.getMiddleName());
        depositor.setLastName(getContent(node, "lastName"));
        logger.debug("Depositor last name set to: {}",
                depositor.getLastName());
        depositor.setIdentification(getContent(node, "identification"));
        logger.debug("Depositor identification set to: {}",
                depositor.getIdentification());
    }
}
