package io.bitsquare.settings;

import com.google.inject.Inject;
import io.bitsquare.bank.BankAccountType;
import io.bitsquare.storage.Storage;
import io.bitsquare.trade.orderbook.OrderBookFilter;

import java.util.*;
import java.util.function.Predicate;

public class Settings
{

    public static Locale locale = Locale.ENGLISH;
    public static Currency currency = Currency.getInstance("USD");


    private Storage storage;
    private OrderBookFilter orderBookFilter;

    public static Locale getLocale()
    {
        return Settings.locale;
    }

    public static Currency getCurrency()
    {
        return Settings.currency;
    }

    @Inject
    public Settings(Storage storage, OrderBookFilter orderBookFilter)
    {
        this.storage = storage;
        this.orderBookFilter = orderBookFilter;


        locale = Locale.ENGLISH;
        currency = Currency.getInstance("USD");

        currency = (Currency) storage.read("Settings.currency");
        if (currency == null)
            currency = Currency.getInstance("USD");
    }


    //TODO remove duplicated entries, insert separators
    public ArrayList<Currency> getAllCurrencies()
    {
        ArrayList<Currency> currencies = new ArrayList<>();
        currencies.add(Currency.getInstance("USD"));
        currencies.add(Currency.getInstance("EUR"));
        currencies.add(Currency.getInstance("CNY"));
        currencies.add(Currency.getInstance("RUB"));

        currencies.add(Currency.getInstance("JPY"));
        currencies.add(Currency.getInstance("GBP"));
        currencies.add(Currency.getInstance("CAD"));
        currencies.add(Currency.getInstance("AUD"));
        currencies.add(Currency.getInstance("CHF"));
        currencies.add(Currency.getInstance("CNY"));

      /*  Set<Currency> otherCurrenciesSet = Currency.getAvailableCurrencies();
        ArrayList<Currency> otherCurrenciesList = new ArrayList<>();
        otherCurrenciesList.addAll(otherCurrenciesSet);
        Collections.sort(otherCurrenciesList, new CurrencyComparator());

        currencies.addAll(otherCurrenciesList); */
        return currencies;
    }

    public ArrayList<BankAccountType> getAllBankAccountTypes()
    {
        ArrayList<BankAccountType> bankTransferTypes = new ArrayList<>();
        bankTransferTypes.add(new BankAccountType(BankAccountType.BankAccountTypeEnum.SEPA, "IBAN", "BIC"));
        bankTransferTypes.add(new BankAccountType(BankAccountType.BankAccountTypeEnum.WIRE, "Prim_todo", "Sec_todo"));
        bankTransferTypes.add(new BankAccountType(BankAccountType.BankAccountTypeEnum.INTERNATIONAL, "Prim_todo", "Sec_todo"));
        bankTransferTypes.add(new BankAccountType(BankAccountType.BankAccountTypeEnum.OK_PAY, "Prim_todo", "Sec_todo"));
        bankTransferTypes.add(new BankAccountType(BankAccountType.BankAccountTypeEnum.NET_TELLER, "Prim_todo", "Sec_todo"));
        bankTransferTypes.add(new BankAccountType(BankAccountType.BankAccountTypeEnum.PERFECT_MONEY, "Prim_todo", "Sec_todo"));
        bankTransferTypes.add(new BankAccountType(BankAccountType.BankAccountTypeEnum.OTHER, "Prim_todo", "Sec_todo"));
        return bankTransferTypes;
    }

    public ArrayList<String> getAllCountries()
    {
        ArrayList<String> bankTransferTypes = new ArrayList<>();
        bankTransferTypes.add("USA");
        bankTransferTypes.add("GB");
        bankTransferTypes.add("DE");
        bankTransferTypes.add("FR");
        bankTransferTypes.add("ES");
        bankTransferTypes.add("CH");
        bankTransferTypes.add("RUS");
        bankTransferTypes.add("AUS");
        bankTransferTypes.add("CAN");
        bankTransferTypes.add("AT");
        return bankTransferTypes;
    }

    public ArrayList<Locale> getAllLocales(String sortField)
    {
        ArrayList<Locale> list = new ArrayList<Locale>(Arrays.asList(Locale.getAvailableLocales()));

        list.removeIf(new Predicate<Locale>()
        {
            @Override
            public boolean test(Locale locale)
            {
                return locale == null || locale.getCountry().equals("") || locale.getLanguage().equals("");
            }
        });

        list.sort(new Comparator()
        {
            @Override
            public int compare(Object o1, Object o2)
            {
                if (sortField.equals("displayCountry"))
                    return (((Locale) o1).getDisplayCountry()).compareTo(((Locale) o2).getDisplayCountry());
                else
                    return 1;
            }
        });
        Locale defaultLocale = Locale.getDefault();
        list.remove(defaultLocale);
        list.add(0, defaultLocale);

        return list;
    }

    /*public ArrayList<String> getAllLanguages()
    {
        ArrayList<String> result = new ArrayList<>();
        for (Locale locale : Locale.getAvailableLocales())
        {
            result.add(locale.getDisplayLanguage());
        }

        return result;
    }  */
    public ArrayList<String> getAllLanguages()
    {
        ArrayList<String> bankTransferTypes = new ArrayList<>();
        bankTransferTypes.add("English");
        bankTransferTypes.add("Chinese");
        bankTransferTypes.add("Spanish");
        bankTransferTypes.add("Russian");
        bankTransferTypes.add("French");
        bankTransferTypes.add("Italian");
        return bankTransferTypes;
    }

    public ArrayList<String> getAllArbitrators()
    {
        ArrayList<String> arbitrators = new ArrayList<>();
        arbitrators.add("Paysty pool 1");
        arbitrators.add("Paysty pool 2");
        arbitrators.add("Paysty pool 3");
        arbitrators.add("Paysty pool 4");
        return arbitrators;
    }

    public ArrayList<String> getAllIdentityVerifications()
    {
        ArrayList<String> identityVerifications = new ArrayList<>();
        identityVerifications.add("Passport");
        identityVerifications.add("PGP");
        identityVerifications.add("BTC-OTC");
        identityVerifications.add("Bitcointalk");
        identityVerifications.add("Reddit");
        identityVerifications.add("Skype");
        identityVerifications.add("Google+");
        identityVerifications.add("Twitter");
        identityVerifications.add("Diaspora");
        identityVerifications.add("Facebook");
        identityVerifications.add("Jabber");
        identityVerifications.add("Other");
        identityVerifications.add("Any");
        identityVerifications.add("None");
        return identityVerifications;
    }

    public ArrayList<String> getAllCollaterals()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("0.01");
        list.add("0.1");
        list.add("0.5");
        list.add("1.0");
        return list;
    }

    public void setCurrency(Currency currency)
    {
        Settings.currency = currency;
        storage.write("Settings.currency", currency);
    }


    public void setLocale(Locale locale)
    {
        Settings.locale = locale;
    }

    public OrderBookFilter getOrderBookFilter()
    {
        return orderBookFilter;
    }


}