package uca.ruiz.antonio.tfgapp.utils;

import java.util.Locale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FechaHoraUtils {
    private static final String UI_FECHA = "dd/MM/yyyy HH:mm";
    private static final String API_FECHA = "yyyy-MM-dd";
    private static final String UI_HORA = "h:mma";
    private static final String API_HORA = "HH:mm:ss";
    private static final String API_FECHA_HORA = "yyyy-MM-dd HH:mm:ss";

    private FechaHoraUtils() {
    }

    public static Date getFechaActual() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    public static String formatoFechaUI(int year, int month, int dayOfMonth) {
        return formatoFechaUI(crearFecha(year, month, dayOfMonth));
    }

    public static String formatoFechaUI(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UI_FECHA, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static Date crearFecha(int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, dayOfMonth);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String formatoFechaAPI(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(API_FECHA, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String formatoHoraUI(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(API_HORA, Locale.getDefault());
        try {
            Date date = simpleDateFormat.parse(time);
            simpleDateFormat.applyPattern(UI_HORA);
            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static Date parseoHoraUI(String timeSchedule) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UI_HORA, Locale.getDefault());
        try {
            return simpleDateFormat.parse(timeSchedule);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String unirFechaHora(Date datePicked, Date timeUi) {
        Calendar datePickedCal = Calendar.getInstance();
        Calendar timeUiCal = Calendar.getInstance();

        datePickedCal.setTime(datePicked);
        timeUiCal.setTime(timeUi);

        datePickedCal.add(Calendar.HOUR_OF_DAY, timeUiCal.get(Calendar.HOUR_OF_DAY));
        datePickedCal.add(Calendar.MINUTE, timeUiCal.get(Calendar.MINUTE));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(API_FECHA_HORA, Locale.getDefault());
        return simpleDateFormat.format(datePickedCal.getTime());
    }
}

