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
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.Optional;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;

/**
 * Class represent realization of SAX API.
 */
public final class BankHandler extends DefaultHandler {

    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger(BankHandler.class);
    /**
     * Root object.
     */
    private Banks banks = new Banks();
    /**
     * Current {@link Bank} object to work with.
     */
    private Bank currentBank;
    /**
     * Current {@link Deposit} object to work with.
     */
    private Deposit currentDeposit;
    /**
     * Current {@link Depositor} object to work with.
     */
    private Depositor currentDepositor;
    /**
     * Current type of element we working on.
     */
    private ElementType type;

    /**
     * Returns {@link #banks} object.
     *
     * @return {@link #banks} object.
     */
    public Banks getBanks() {
        return banks;
    }

    @Override
    public void startElement(final String uri,
                             final String localName,
                             final String qName,
                             final Attributes attr) {
        type = ElementType.fromValue(localName);
        logger.debug("element: {}", localName);
        if (type == null) {
            return;
        }
        switch (type) {
            case BANK:
                handleBank(attr);
                break;
            case DEPOSIT:
                handleDeposit(attr);
                break;
            case DEPOSITOR:
                currentDepositor = new Depositor();
                currentDeposit.setDepositor(currentDepositor);
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(final String uri,
                           final String localName,
                           final String qName) {
        if (localName.equalsIgnoreCase("bank")) {
            banks.addBank(currentBank);
        }
        if (localName.equalsIgnoreCase("deposit")) {
            currentBank.addDeposit(currentDeposit);
        }
    }

    @Override
    public void characters(final char[] ch,
                           final int start,
                           final int length) {
        String str = String.valueOf(ch, start, length);
        logger.debug("element value: {}", str);
        switch (type) {
            case NAME:
                currentBank.setName(str);
                break;
            case TYPE:
                currentDeposit.setType(str);
                break;
            case IBAN:
                currentDeposit.setIban(str);
                break;
            case FIRSTNAME:
                currentDepositor.setFirstName(str);
                break;
            case MIDDLENAME:
                currentDepositor.setMiddleName(str);
                break;
            case LASTNAME:
                currentDepositor.setLastName(str);
                break;
            case IDENTIFICATION:
                currentDepositor.setIdentification(str);
                break;
            case DEPOSITDATE:
                currentDeposit.setDepositDate(LocalDate.parse(str));
                break;
            case AMOUNT:
                currentDeposit.setAmount(parseDouble(str));
                break;
            case CURRENCY:
                currentDeposit.setCurrency(Currency.fromValue(str));
                break;
            case PROFITABILITY:
                currentDeposit.setProfitability(parseDouble(str));
                break;
            case TERM:
                currentDeposit.setTerm(str);
                break;
            case WITHDRAWAL:
                currentDeposit.setWithdrawal(parseBoolean(str));
                break;
            case REFILL:
                currentDeposit.setRefill(parseBoolean(str));
                break;
            case CAPITALIZATION:
                currentDeposit.setCapitalization(parseBoolean(str));
                break;
            case PAYOUT:
                ((SavingDeposit) currentDeposit).setPayout(str);
                break;
            case MINBALANCE:
                ((SettlementDeposit) currentDeposit)
                        .setMinBalance(parseDouble(str));
                break;
            default:
                break;
        }
    }

    /**
     * Handles the {@code bank} element.
     *
     * @param attr attributes from the element.
     */
    private void handleBank(final Attributes attr) {
        currentBank = new Bank();
        for (int i = 0; i < attr.getLength(); i++) {
            switch (attr.getLocalName(i)) {
                case "country":
                    currentBank.setCountry(attr.getValue(i));
                    break;
                case "site":
                    currentBank.setSite(attr.getValue(i));
                    break;
                case "licence":
                    currentBank
                            .setLicence(parseBoolean(attr.getValue(i)));
                    break;
                default:
                    logger.info("Unknown attribute {}", attr.getValue(i));
                    break;
            }
        }
    }

    /**
     * Handles the {@code deposit} element.
     *
     * @param attr attributes from the element.
     */
    private void handleDeposit(final Attributes attr) {
        Optional<String> type0 = Optional.ofNullable(attr.getValue("xsi:type"));
        String id0 = attr.getValue("ID");
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
}
