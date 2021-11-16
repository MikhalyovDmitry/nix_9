package ua.com.alevel;

import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class ExceptionMain extends JFrame {

    private final DefaultListModel<String> dlm = new DefaultListModel<>();
    boolean[] format = new boolean[21];
    double timeInMillis;
    double[] timeBase = new double[13];
    int index = 0;
    double differenceTimeOne;
    double differenceTimeTwo;
    double calculateTime;
    double calculateResult;
    public ExceptionMain() {
        JFrame f = new JFrame("TIME");

        JTextField yearInput = new JTextField("year");
        JTextField monthInput = new JTextField("month");
        JTextField dayInput = new JTextField("day");
        JTextField hourInput = new JTextField("hour");
        JTextField minuteInput = new JTextField("minute");
        JTextField secondInput = new JTextField("second");
        JTextField millisInput = new JTextField("millis");
        JLabel dateSeparatorOne = new JLabel();
        JLabel dateSeparatorTwo = new JLabel();
        JLabel timeSeparatorOne = new JLabel(":");
        JLabel timeSeparatorTwo = new JLabel(":");
        JLabel timeSeparatorThree = new JLabel(":");

        JTextField test = new JTextField();
        test.setBounds(20, 300, 400, 30);
        test.setVisible(false);
        f.add(test);

        JLabel inputFormat = new JLabel("Input format:");
        inputFormat.setBounds(20, 20, 100, 30);

        JLabel year = new JLabel("Year");
        JRadioButton yy = new JRadioButton("yy");
        JRadioButton yyyy = new JRadioButton("yyyy");
        yyyy.setSelected(true); // default
        year.setBounds(20, 50, 50, 30);
        yy.setBounds(150, 50, 50, 30);
        yyyy.setBounds(200, 50, 50, 30);

        JLabel month = new JLabel("Month");
        JRadioButton m = new JRadioButton("m");
        JRadioButton mm = new JRadioButton("mm");
        mm.setSelected(true); // default
        JRadioButton mmm = new JRadioButton("mmm");
        month.setBounds(20, 80, 50, 30);
        m.setBounds(100, 80, 50, 30);
        mm.setBounds(150, 80, 50, 30);
        mmm.setBounds(200, 80, 70, 30);

        JLabel day = new JLabel("Day");
        JRadioButton d = new JRadioButton("d");
        JRadioButton dd = new JRadioButton("dd");
        dd.setSelected(true); // default
        day.setBounds(20, 110, 50, 30);
        d.setBounds(150, 110, 50, 30);
        dd.setBounds(200, 110, 50, 30);

        JLabel hour = new JLabel("Hour");
        JRadioButton hourShow = new JRadioButton("show");
        hour.setBounds(20, 140, 50, 30);
        hourShow.setBounds(200, 140, 70, 30);

        JLabel minute = new JLabel("Minute");
        JRadioButton minuteShow = new JRadioButton("show");
        minute.setBounds(20, 170, 50, 30);
        minuteShow.setBounds(200, 170, 70, 30);

        JLabel second = new JLabel("Second");
        JRadioButton secondShow = new JRadioButton("show");
        second.setBounds(20, 200, 50, 30);
        secondShow.setBounds(200, 200, 100, 30);

        JLabel millis = new JLabel("Millis");
        JRadioButton millisShow = new JRadioButton("show");
        millis.setBounds(20, 230, 50, 30);
        millisShow.setBounds(200, 230, 70, 30);

        JLabel separator = new JLabel("Separator");
        JRadioButton slash = new JRadioButton("/");
        slash.setSelected(true); // default
        JRadioButton minus = new JRadioButton("-");
        separator.setBounds(20, 260, 100, 30);
        slash.setBounds(150, 260, 50, 30);
        minus.setBounds(200, 260, 50, 30);

        JLabel first = new JLabel("First");
        JRadioButton yearFirst = new JRadioButton("");
        JRadioButton monthFirst = new JRadioButton("");
        JRadioButton dayFirst = new JRadioButton("");
        dayFirst.setSelected(true); // default
        JLabel last = new JLabel("Last");
        JRadioButton yearLast = new JRadioButton("");
        yearLast.setSelected(true); // default
        JRadioButton monthLast = new JRadioButton("");
        JRadioButton dayLast = new JRadioButton("");

        first.setBounds(270, 20, 50, 30);
        yearFirst.setBounds(270, 50, 50, 30);
        monthFirst.setBounds(270, 80, 50, 30);
        dayFirst.setBounds(270, 110, 50, 30);
        last.setBounds(320, 20, 50, 30);
        yearLast.setBounds(320, 50, 50, 30);
        monthLast.setBounds(320, 80, 50, 30);
        dayLast.setBounds(320, 110, 50, 30);

        JRadioButton dateFirstButton = new JRadioButton("date first");
        dateFirstButton.setSelected(true); // default
        JRadioButton timeFirstButton = new JRadioButton("time first");
        dateFirstButton.setBounds(270, 140, 100, 30);
        timeFirstButton.setBounds(270, 170, 100, 30);
        f.add(dateFirstButton);
        f.add(timeFirstButton);

        ActionListener showInputTextFields = e -> {
            JTextField[] inputOrder = new JTextField[7];
            JTextField[] dateInputOrder = new JTextField[3];
            JTextField[] timeInputOrder = new JTextField[4];

            yearInput.setVisible(false);
            monthInput.setVisible(false);
            dayInput.setVisible(false);
            hourInput.setVisible(false);
            minuteInput.setVisible(false);
            secondInput.setVisible(false);
            millisInput.setVisible(false);
            test.setVisible(false);

            if (yearFirst.isSelected() && yearLast.isSelected()
                    || monthFirst.isSelected() && monthLast.isSelected()
                    || dayFirst.isSelected() && dayLast.isSelected()) {
                test.setText("First and Last должны отличаться!");
                test.setVisible(true);
                return;
            }
            if (!hourShow.isSelected()) {
                if (minuteShow.isSelected()) {
                    test.setText("Время без часов, но с минутами? Серьёзно?");
                    test.setVisible(true);
                    return;
                }
                if (secondShow.isSelected()) {
                    test.setText("Время без часов, но с секундами? Серьёзно?");
                    test.setVisible(true);
                    return;
                }
                if (millisShow.isSelected()) {
                    test.setText("Время без часов, но с миллисекундами? Серьёзно?");
                    test.setVisible(true);
                    return;
                }
            }
            if (hourShow.isSelected() && !minuteShow.isSelected()) {
                if (secondShow.isSelected()) {
                    test.setText("Часы:секунды, серьёзно? Кому нужен такой формат времени?");
                    test.setVisible(true);
                    return;
                }
                if (millisShow.isSelected()) {
                    test.setText("Часы:миллисекунды, серьёзно? Кому нужен такой формат времени?");
                    test.setVisible(true);
                    return;
                }
            }
            if (hourShow.isSelected() && minuteShow.isSelected() && !secondShow.isSelected()) {
                if (millisShow.isSelected()) {
                    test.setText("Часы:минуты:миллисекунды, серьёзно? Кому нужен такой формат времени?");
                    test.setVisible(true);
                    return;
                }
            }

            String yearField = null;
            if (yy.isSelected()) yearField = yy.getText();
            if (yyyy.isSelected()) yearField = yyyy.getText();
            yearInput.setText(yearField);
            String monthField = null;
            if (m.isSelected()) monthField = m.getText();
            if (mm.isSelected()) monthField = mm.getText();
            if (mmm.isSelected()) monthField = mmm.getText();
            monthInput.setText(monthField);
            String dayField = null;
            if (d.isSelected()) dayField = d.getText();
            if (dd.isSelected()) dayField = dd.getText();
            dayInput.setText(dayField);
            String hourField = null;
            if (hourShow.isSelected()) hourField = "hour";
            hourInput.setText(hourField);
            String minuteField = null;
            if (minuteShow.isSelected()) minuteField = "min";
            minuteInput.setText(minuteField);
            String secondField = null;
            if (secondShow.isSelected()) secondField = "sec";
            secondInput.setText(secondField);
            String millisField = null;
            if (millisShow.isSelected()) millisField = "mil";
            millisInput.setText(millisField);

            if (yearFirst.isSelected()) {
                dateInputOrder[0] = yearInput;
            }
            if (monthFirst.isSelected()) {
                dateInputOrder[0] = monthInput;
            }
            if (dayFirst.isSelected()) {
                dateInputOrder[0] = dayInput;
            }
            if (yearLast.isSelected()) {
                dateInputOrder[2] = yearInput;
            }
            if (monthLast.isSelected()) {
                dateInputOrder[2] = monthInput;
            }
            if (dayLast.isSelected()) {
                dateInputOrder[2] = dayInput;
            }
            if (!yearFirst.isSelected() && !yearLast.isSelected()) dateInputOrder[1] = yearInput;
            if (!monthFirst.isSelected() && !monthLast.isSelected()) dateInputOrder[1] = monthInput;
            if (!dayFirst.isSelected() && !dayLast.isSelected()) dateInputOrder[1] = dayInput;

            int timeSelected = 0;
            if (hourShow.isSelected()) {
                timeInputOrder[0] = hourInput;
                timeSelected++;
            }
            if (minuteShow.isSelected()) {
                timeInputOrder[1] = minuteInput;
                timeSelected++;
            }
            if (secondShow.isSelected()) {
                timeInputOrder[2] = secondInput;
                timeSelected++;
            }
            if (millisShow.isSelected()) {
                timeInputOrder[3] = millisInput;
                timeSelected++;
            }
            if (dateFirstButton.isSelected()) {
                System.arraycopy(dateInputOrder, 0, inputOrder, 0, dateInputOrder.length);
                System.arraycopy(timeInputOrder, 0, inputOrder, 3, timeSelected);
            }
            if (timeFirstButton.isSelected()) {
                System.arraycopy(timeInputOrder, 0, inputOrder, 0, timeSelected);
                System.arraycopy(dateInputOrder, 0, inputOrder, timeSelected, dateInputOrder.length);
            }

            int plusX = 0;
            String separator1 = null;
            if (slash.isSelected()) separator1 = "/";
            if (minus.isSelected()) separator1 = "-";
            if (dateFirstButton.isSelected()) {
                dateSeparatorOne.setBounds(72, 300, 15, 30);
                dateSeparatorTwo.setBounds(132, 300, 15, 30);
            } else {
                dateSeparatorOne.setBounds(72 + timeSelected * 60, 300, 15, 30);
                dateSeparatorTwo.setBounds(132 + timeSelected * 60, 300, 15, 30);
            }
            dateSeparatorOne.setText(separator1);
            dateSeparatorTwo.setText(separator1);
            dateSeparatorOne.setVisible(true);
            dateSeparatorTwo.setVisible(true);
            f.add(dateSeparatorOne);
            f.add(dateSeparatorTwo);

            timeSeparatorOne.setVisible(false);
            timeSeparatorTwo.setVisible(false);
            timeSeparatorThree.setVisible(false);
            if (timeFirstButton.isSelected()) {
                if (timeSelected > 1) {
                    timeSeparatorOne.setBounds(72, 300, 15, 30);
                    timeSeparatorOne.setVisible(true);
                }
                if (timeSelected > 2) {
                    timeSeparatorTwo.setBounds(132, 300, 15, 30);
                    timeSeparatorTwo.setVisible(true);
                }
                if (timeSelected > 3) {
                    timeSeparatorThree.setBounds(192, 300, 15, 30);
                    timeSeparatorThree.setVisible(true);
                }
            } else {
                if (timeSelected > 1) {
                    timeSeparatorOne.setBounds(252, 300, 15, 30);
                    timeSeparatorOne.setVisible(true);
                }
                if (timeSelected > 2) {
                    timeSeparatorTwo.setBounds(312, 300, 15, 30);
                    timeSeparatorTwo.setVisible(true);
                }
                if (timeSelected > 3) {
                    timeSeparatorThree.setBounds(372, 300, 15, 30);
                    timeSeparatorThree.setVisible(true);
                }
            }
            f.add(timeSeparatorOne);
            f.add(timeSeparatorTwo);
            f.add(timeSeparatorThree);

            for (JTextField jTextField : inputOrder) {
                if (jTextField != null) {
                    jTextField.setBounds(20 + plusX, 300, 50, 30);
                    jTextField.setVisible(true);
                    f.add(jTextField);
                    plusX += 60;
                }
            }
        };

        JButton showInputConstructor = new JButton("REFRESH");
        showInputConstructor.setToolTipText("Нажмите эту кнопку, чтобы обновить формат ввода");
        showInputConstructor.setBounds(275, 250, 145, 30);
        showInputConstructor.addActionListener(showInputTextFields);
        showInputConstructor.doClick(3);

        ButtonGroup whatIsFirst = new ButtonGroup();
        whatIsFirst.add(dateFirstButton);
        whatIsFirst.add(timeFirstButton);

        ButtonGroup yearButtongroup = new ButtonGroup();
        yearButtongroup.add(yy);
        yearButtongroup.add(yyyy);

        ButtonGroup monthButtonGroup = new ButtonGroup();
        monthButtonGroup.add(m);
        monthButtonGroup.add(mm);
        monthButtonGroup.add(mmm);

        ButtonGroup dayButtongroup = new ButtonGroup();
        dayButtongroup.add(d);
        dayButtongroup.add(dd);

        ButtonGroup separators = new ButtonGroup();
        separators.add(slash);
        separators.add(minus);

        ButtonGroup firstGroup = new ButtonGroup();
        firstGroup.add(yearFirst);
        firstGroup.add(monthFirst);
        firstGroup.add(dayFirst);

        ButtonGroup lastGroup = new ButtonGroup();
        lastGroup.add(yearLast);
        lastGroup.add(monthLast);
        lastGroup.add(dayLast);

        f.add(inputFormat);
        f.add(year);
        f.add(yy);
        f.add(yyyy);
        f.add(month);
        f.add(m);
        f.add(mm);
        f.add(mmm);
        f.add(day);
        f.add(d);
        f.add(dd);
        f.add(hour);
        f.add(hourShow);
        f.add(minute);
        f.add(minuteShow);
        f.add(second);
        f.add(secondShow);
        f.add(millis);
        f.add(millisShow);
        f.add(separator);
        f.add(slash);
        f.add(minus);
        f.add(first);
        f.add(last);
        f.add(yearFirst);
        f.add(monthFirst);
        f.add(dayFirst);
        f.add(yearLast);
        f.add(monthLast);
        f.add(dayLast);
        f.add(showInputConstructor);

        int x = 500;
        JTextField testOut = new JTextField();
        testOut.setBounds(20 + x, 300, 400, 30);
        testOut.setEditable(false);

        f.add(testOut);

        JLabel outputFormat = new JLabel("Output format:");
        outputFormat.setBounds(20 + x, 20, 100, 30);

        JLabel yearOut = new JLabel("Year");
        JRadioButton yyOut = new JRadioButton("yy");
        JRadioButton yyyyOut = new JRadioButton("yyyy");
        yyyyOut.setSelected(true); // default
        yearOut.setBounds(20 + x, 50, 50, 30);
        yyOut.setBounds(150 + x, 50, 50, 30);
        yyyyOut.setBounds(200 + x, 50, 50, 30);

        JLabel monthOut = new JLabel("Month");
        JRadioButton mOut = new JRadioButton("m");
        JRadioButton mmOut = new JRadioButton("mm");
        mmOut.setSelected(true); // default
        JRadioButton mmmOut = new JRadioButton("mmm");
        monthOut.setBounds(20 + x, 80, 50, 30);
        mOut.setBounds(100 + x, 80, 50, 30);
        mmOut.setBounds(150 + x, 80, 50, 30);
        mmmOut.setBounds(200 + x, 80, 70, 30);

        JLabel dayOut = new JLabel("Day");
        JRadioButton dOut = new JRadioButton("d");
        JRadioButton ddOut = new JRadioButton("dd");
        ddOut.setSelected(true); // default
        dayOut.setBounds(20 + x, 110, 50, 30);
        dOut.setBounds(150 + x, 110, 50, 30);
        ddOut.setBounds(200 + x, 110, 50, 30);

        JLabel hourOut = new JLabel("Hour");
        JRadioButton hourShowOut = new JRadioButton("show");
        hourOut.setBounds(20 + x, 140, 50, 30);
        hourShowOut.setBounds(200 + x, 140, 70, 30);

        JLabel minuteOut = new JLabel("Minute");
        JRadioButton minuteShowOut = new JRadioButton("show");
        minuteOut.setBounds(20 + x, 170, 50, 30);
        minuteShowOut.setBounds(200 + x, 170, 70, 30);

        JLabel secondOut = new JLabel("Second");
        JRadioButton secondShowOut = new JRadioButton("show");
        secondOut.setBounds(20 + x, 200, 50, 30);
        secondShowOut.setBounds(200 + x, 200, 100, 30);

        JLabel millisOut = new JLabel("Millis");
        JRadioButton millisShowOut = new JRadioButton("show");
        millisOut.setBounds(20 + x, 230, 50, 30);
        millisShowOut.setBounds(200 + x, 230, 70, 30);

        JLabel separatorOut = new JLabel("Separator");
        JRadioButton slashOut = new JRadioButton("/");
        slashOut.setSelected(true); // default
        JRadioButton minusOut = new JRadioButton("-");
        separatorOut.setBounds(20 + x, 260, 100, 30);
        slashOut.setBounds(150 + x, 260, 50, 30);
        minusOut.setBounds(200 + x, 260, 50, 30);

        JLabel firstOut = new JLabel("First");
        JRadioButton yearFirstOut = new JRadioButton("");
        JRadioButton monthFirstOut = new JRadioButton("");
        JRadioButton dayFirstOut = new JRadioButton("");
        dayFirstOut.setSelected(true); // default
        JLabel lastOut = new JLabel("Last");
        JRadioButton yearLastOut = new JRadioButton("");
        yearLastOut.setSelected(true); // default
        JRadioButton monthLastOut = new JRadioButton("");
        JRadioButton dayLastOut = new JRadioButton("");

        firstOut.setBounds(270 + x, 20, 50, 30);
        yearFirstOut.setBounds(270 + x, 50, 50, 30);
        monthFirstOut.setBounds(270 + x, 80, 50, 30);
        dayFirstOut.setBounds(270 + x, 110, 50, 30);
        lastOut.setBounds(320 + x, 20, 50, 30);
        yearLastOut.setBounds(320 + x, 50, 50, 30);
        monthLastOut.setBounds(320 + x, 80, 50, 30);
        dayLastOut.setBounds(320 + x, 110, 50, 30);

        JRadioButton dateFirstButtonOut = new JRadioButton("date first");
        dateFirstButtonOut.setSelected(true); // default
        JRadioButton timeFirstButtonOut = new JRadioButton("time first");
        dateFirstButtonOut.setBounds(270 + x, 140, 100, 30);
        timeFirstButtonOut.setBounds(270 + x, 170, 100, 30);
        f.add(dateFirstButtonOut);
        f.add(timeFirstButtonOut);

        ButtonGroup whatIsFirstOut = new ButtonGroup();
        whatIsFirstOut.add(dateFirstButtonOut);
        whatIsFirstOut.add(timeFirstButtonOut);

        ButtonGroup yearButtongroupOut = new ButtonGroup();
        yearButtongroupOut.add(yyOut);
        yearButtongroupOut.add(yyyyOut);

        ButtonGroup monthButtonGroupOut = new ButtonGroup();
        monthButtonGroupOut.add(mOut);
        monthButtonGroupOut.add(mmOut);
        monthButtonGroupOut.add(mmmOut);

        ButtonGroup dayButtongroupOut = new ButtonGroup();
        dayButtongroupOut.add(dOut);
        dayButtongroupOut.add(ddOut);

        ButtonGroup separatorsOut = new ButtonGroup();
        separatorsOut.add(slashOut);
        separatorsOut.add(minusOut);

        ButtonGroup firstGroupOut = new ButtonGroup();
        firstGroupOut.add(yearFirstOut);
        firstGroupOut.add(monthFirstOut);
        firstGroupOut.add(dayFirstOut);

        ButtonGroup lastGroupOut = new ButtonGroup();
        lastGroupOut.add(yearLastOut);
        lastGroupOut.add(monthLastOut);
        lastGroupOut.add(dayLastOut);

        f.add(outputFormat);
        f.add(yearOut);
        f.add(yyOut);
        f.add(yyyyOut);
        f.add(monthOut);
        f.add(mOut);
        f.add(mmOut);
        f.add(mmmOut);
        f.add(dayOut);
        f.add(dOut);
        f.add(ddOut);
        f.add(hourOut);
        f.add(hourShowOut);
        f.add(minuteOut);
        f.add(minuteShowOut);
        f.add(secondOut);
        f.add(secondShowOut);
        f.add(millisOut);
        f.add(millisShowOut);
        f.add(separatorOut);
        f.add(slashOut);
        f.add(minusOut);
        f.add(firstOut);
        f.add(lastOut);
        f.add(yearFirstOut);
        f.add(monthFirstOut);
        f.add(dayFirstOut);
        f.add(yearLastOut);
        f.add(monthLastOut);
        f.add(dayLastOut);
        //f.add(showInputConstructorOut);

        ActionListener convertInputToOutput = e -> {
            int yearInt = 0;
            int monthInt = 0;
            int dayInt = 0;
            int hourInt = 0;
            int minuteInt = 0;
            int secondInt = 0;
            int millisInt = 0;

            if (!dateFirstButtonOut.isSelected() && !timeFirstButtonOut.isSelected()) {
                testOut.setText("Обязательно укажите, что будете вводить сначала, дату или время!");
                testOut.setVisible(true);
                return;
            }
            if (!yearFirstOut.isSelected() && !monthFirstOut.isSelected() && !dayFirstOut.isSelected()) {
                testOut.setText("Обязательно укажите, что будете вводить сначала, год, месяц, или день");
                testOut.setVisible(true);
                return;
            }
            if (!yearLastOut.isSelected() && !monthLastOut.isSelected() && !dayLastOut.isSelected()) {
                testOut.setText("Обязательно укажите, что будете вводить в конце, год, месяц, или день");
                testOut.setVisible(true);
                return;
            }
            if (!slashOut.isSelected() && !minusOut.isSelected()) {
                testOut.setText("Обязательно укажите разделитель для даты");
                testOut.setVisible(true);
                return;
            }
            if (yearFirstOut.isSelected() && yearLastOut.isSelected()
                    || monthFirstOut.isSelected() && monthLastOut.isSelected()
                    || dayFirstOut.isSelected() && dayLastOut.isSelected()) {
                testOut.setText("First and Last должны отличаться!");
                testOut.setVisible(true);
                return;
            }
            if (!hourShowOut.isSelected()) {
                if (minuteShowOut.isSelected()) {
                    testOut.setText("Время без часов, но с минутами? Серьёзно?");
                    testOut.setVisible(true);
                    return;
                }
                if (secondShowOut.isSelected()) {
                    testOut.setText("Время без часов, но с секундами? Серьёзно?");
                    testOut.setVisible(true);
                    return;
                }
                if (millisShowOut.isSelected()) {
                    testOut.setText("Время без часов, но с миллисекундами? Серьёзно?");
                    testOut.setVisible(true);
                    return;
                }
            }
            if (hourShowOut.isSelected() && !minuteShowOut.isSelected()) {
                if (secondShowOut.isSelected()) {
                    testOut.setText("Часы:секунды, серьёзно? Кому нужен такой формат времени?");
                    testOut.setVisible(true);
                    return;
                }
                if (millisShowOut.isSelected()) {
                    testOut.setText("Часы:миллисекунды, серьёзно? Кому нужен такой формат времени?");
                    testOut.setVisible(true);
                    return;
                }
            }
            if (hourShowOut.isSelected() && minuteShowOut.isSelected() && !secondShowOut.isSelected()) {
                if (millisShowOut.isSelected()) {
                    testOut.setText("Часы:минуты:миллисекунды, серьёзно? Кому нужен такой формат времени?");
                    testOut.setVisible(true);
                    return;
                }
            }
            if (!yyOut.isSelected() && !yyyyOut.isSelected()) {
                testOut.setText("Обязательно укажите формат ввода года yy / yyyy");
                testOut.setVisible(true);
                return;
            }
            if (!mOut.isSelected() && !mmOut.isSelected() && !mmmOut.isSelected()) {
                testOut.setText("Обязательно укажите формат ввода месяца m / mm / mmm");
                testOut.setVisible(true);
                return;
            }
            if (!dOut.isSelected() && !ddOut.isSelected()) {
                testOut.setText("Обязательно укажите формат ввода дня d / dd");
                testOut.setVisible(true);
                return;
            }

            StringBuilder errorMessage = new StringBuilder();
            boolean error = false;
            String yearMode = null;
            if (yy.isSelected()) yearMode = yy.getText();
            if (yyyy.isSelected()) yearMode = yyyy.getText();
            if (!isInputYearOk(yearInput.getText(), yearMode)) {
                errorMessage.append("Wrong year! ");
                error = true;
            } else {
                if (yearInput.getText().equals("")) {
                    yearInt = 0;
                } else {
                    yearInt = Integer.parseInt(yearInput.getText());
                }
            }
            String monthMode = null;
            if (m.isSelected()) monthMode = m.getText();
            if (mm.isSelected()) monthMode = mm.getText();
            if (mmm.isSelected()) monthMode = mmm.getText();
            if (!isInputMonthOk(monthInput.getText(), monthMode)) {
                errorMessage.append("Wrong month! ");
                error = true;
            } else {
                if (monthInput.getText().equals("")) {
                    monthInt = 1;
                } else {
                    if (monthMode.equals("mmm")) {
                        monthInt = Time.monthMmm(monthInput.getText());
                    } else {
                        monthInt = Integer.parseInt(monthInput.getText());
                    }
                }
            }
            String dayMode = null;
            if (d.isSelected()) dayMode = d.getText();
            if (dd.isSelected()) dayMode = dd.getText();
            if (!isInputDayOk(dayInput.getText(), monthInt, yearInt, dayMode)) {
                errorMessage.append("Wrong day! ");
                error = true;
            } else {
                if (dayInput.getText().equals("")) {
                    dayInt = 1;
                } else {
                    dayInt = Integer.parseInt(dayInput.getText());
                }
            }
            if (!isInputHourOk(hourInput.getText())) {
                errorMessage.append("Wrong hour! ");
                error = true;
            } else {
                if (hourInput.getText().equals("")) {
                    hourInt = 0;
                } else {
                    hourInt = Integer.parseInt(hourInput.getText());
                }
            }
            if (!isInputMinuteOk(minuteInput.getText())) {
                errorMessage.append("Wrong minute! ");
                error = true;
            } else {
                if (minuteInput.getText().equals("")) {
                    minuteInt = 0;
                } else {
                    minuteInt = Integer.parseInt(minuteInput.getText());
                }
            }
            if (!isInputSecondOk(secondInput.getText())) {
                errorMessage.append("Wrong second! ");
                error = true;
            } else {
                if (secondInput.getText().equals("")) {
                    secondInt = 0;
                } else {
                    secondInt = Integer.parseInt(secondInput.getText());
                }
            }
            if (!isInputMillisOk(millisInput.getText())) {
                errorMessage.append("Wrong millis! ");
                error = true;
            } else {
                if (millisInput.getText().equals("")) {
                    millisInt = 0;
                } else {
                    millisInt = Integer.parseInt(millisInput.getText());
                }
            }
            if (error) {
                testOut.setText(errorMessage.toString());
                testOut.setVisible(true);
                return;
            }

            boolean yy1 = yyOut.isSelected();
            boolean yyyy1 = yyyyOut.isSelected();
            boolean m1 = mOut.isSelected();
            boolean mm1 = mmOut.isSelected();
            boolean mmm1 = mmmOut.isSelected();
            boolean d1 = dOut.isSelected();
            boolean dd1 = ddOut.isSelected();
            boolean yearFirst1 = yearFirstOut.isSelected();
            boolean yearLast1 = yearLastOut.isSelected();
            boolean monthFirst1 = monthFirstOut.isSelected();
            boolean monthLast1 = monthLastOut.isSelected();
            boolean dayFirst1 = dayFirstOut.isSelected();
            boolean dayLast1 = dayLastOut.isSelected();
            boolean slash1 = slashOut.isSelected();
            boolean minus1 = minusOut.isSelected();
            boolean dateFirst = dateFirstButtonOut.isSelected();
            boolean timeFirst = timeFirstButtonOut.isSelected();
            boolean hour1 = hourShowOut.isSelected();
            boolean minute1 = minuteShowOut.isSelected();
            boolean second1 = secondShowOut.isSelected();
            boolean millis1 = millisShowOut.isSelected();

            format = new boolean[]{yy1, yyyy1, m1, mm1, mmm1, d1, dd1,
                    yearFirst1, yearLast1, monthFirst1, monthLast1, dayFirst1, dayLast1,
                    slash1, minus1, dateFirst, timeFirst, hour1, minute1, second1, millis1};

            timeInMillis = Time.inputTime(yearInt, monthInt, dayInt, hourInt, minuteInt, secondInt, millisInt);
            testOut.setText(millisToString(timeInMillis, format));
            testOut.setVisible(true);
        };

        JButton convert = new JButton("CONVERT");
        convert.setBounds(425, 300, 90, 30);
        convert.setVisible(true);
        convert.addActionListener(convertInputToOutput);
        f.add(convert);

        JLabel difference = new JLabel("Расчёт разницы между датами");
        difference.setBounds(20, 330, 200, 30);
        JTextField differenceInputOne = new JTextField();
        differenceInputOne.setBounds(20, 360, 400, 30);
        differenceInputOne.setEditable(false);
        JTextField differenceInputTwo = new JTextField();
        differenceInputTwo.setBounds(20, 390, 400, 30);
        differenceInputTwo.setEditable(false);
        JTextField differenceResult = new JTextField();
        differenceResult.setBounds(70, 420, 270, 30);
        differenceResult.setEditable(false);
        String[] units = new String[]{"Years", "Days", "Hours", "Minutes", "Seconds", "Millis"};
        JComboBox<? extends String> outputUnits = new JComboBox<>(units);
        outputUnits.setBounds(340, 420, 80, 30);
        f.add(differenceInputOne);
        f.add(differenceInputTwo);
        f.add(differenceResult);
        f.add(difference);
        f.add(outputUnits);
        f.add(outputUnits);

        ActionListener calculateDifference = e -> {
            String unit = outputUnits.getSelectedItem().toString();
            if (differenceTimeOne == 0.0 || differenceTimeTwo == 0.0) {
                differenceResult.setText("Пожалуйста, введите что-нибудь");
                return;
            }
            double outputTime = Math.abs(differenceTimeOne - differenceTimeTwo);
            int years = (int) Math.floor(outputTime / 1000 / 60 / 60 / 24 / 365);
            int days = (int) Math.floor(outputTime / 1000 / 60 / 60 / 24);
            int hours = (int) Math.floor(outputTime / 1000 / 60 / 60);
            int minutes = (int) Math.floor(outputTime / 1000 / 60);
            int seconds = (int) Math.floor(outputTime / 1000);
            int millis12 = (int) Math.floor(outputTime);
            if (unit.equals("Years")) {
                differenceResult.setText(String.valueOf(years));
            }
            if (unit.equals("Days")) {
                differenceResult.setText(String.valueOf(days));
            }
            if (unit.equals("Hours")) {
                differenceResult.setText(String.valueOf(hours));
            }
            if (unit.equals("Minutes")) {
                differenceResult.setText(String.valueOf(minutes));
            }
            if (unit.equals("Seconds")) {
                differenceResult.setText(String.valueOf(seconds));
            }
            if (unit.equals("Millis")) {
                differenceResult.setText(String.valueOf(millis12));
            }
        };

        JButton equal = new JButton("=");
        equal.setBounds(20, 420, 50, 30);
        equal.addActionListener(calculateDifference);
        JTextField resultText = new JTextField();
        resultText.setBounds(70, 570, 350, 30);
        resultText.setEditable(false);
        f.add(equal);

        JLabel calculator = new JLabel("Калькулятор");
        calculator.setBounds(20, 480, 200, 30);
        JTextField calculatorInputOne = new JTextField();
        calculatorInputOne.setBounds(20, 510, 400, 30);
        calculatorInputOne.setEditable(false);
        JTextField calculatorInputTwo = new JTextField();
        calculatorInputTwo.setBounds(70, 540, 270, 30);
        String[] pm = new String[]{"+", "-"};
        JComboBox<? extends String> plusMinus = new JComboBox<>(pm);
        plusMinus.setBounds(20, 540, 50, 30);
        JComboBox<? extends String> inputUnits = new JComboBox<>(units);
        inputUnits.setBounds(340, 540, 80, 30);

        ActionListener calculate = e -> {
            String unit = inputUnits.getSelectedItem().toString();
            String pm1 = plusMinus.getSelectedItem().toString();
            double millisToCalculate = 0d;
            if (calculatorInputTwo.getText().isEmpty()) {
                resultText.setText("Пожалуйста, введите что-нибудь");
                return;
            }
            if (isNotNumber(calculatorInputTwo.getText())) {
                resultText.setText("Пожалуйста, используйте только цифры");
                return;
            }

            try {
                Integer.parseInt(calculatorInputTwo.getText());
            } catch (NumberFormatException ex) {
                if (ex != null)
                    resultText.setText("Слишком большое число");
                return;
            }

            double inputInt = Integer.parseInt(calculatorInputTwo.getText());
            if (unit.equals("Years"))
                millisToCalculate = (inputInt * 1000 * 60 * 60 * 24 * 365.25);
            if (unit.equals("Days"))
                millisToCalculate = (inputInt * 1000 * 60 * 60 * 24);
            if (unit.equals("Hours"))
                millisToCalculate = (inputInt * 1000 * 60 * 60);
            if (unit.equals("Minutes"))
                millisToCalculate = (inputInt * 1000 * 60);
            if (unit.equals("Seconds"))
                millisToCalculate = inputInt * 1000;
            if (unit.equals("Millis"))
                millisToCalculate = inputInt;

            if (pm1.equals("-")) {
                calculateResult = calculateTime - millisToCalculate;
            }
            if (pm1.equals("+")) {
                calculateResult = calculateTime + millisToCalculate;
            }
            resultText.setText(millisToString(calculateResult, format));
        };

        JButton result = new JButton("=");
        result.setBounds(20, 570, 50, 30);
        result.addActionListener(calculate);

        f.add(calculator);
        f.add(calculatorInputOne);
        f.add(calculatorInputTwo);
        f.add(inputUnits);
        f.add(plusMinus);
        f.add(result);
        f.add(resultText);

        JLabel maximumReached = new JLabel();
        maximumReached.setBounds(640, 330, 160, 30);
        f.add(maximumReached);

        ActionListener writeToTimeBase = e -> {
            if (index > 12) {
                maximumReached.setText("Sorry! That's the end of list");
                maximumReached.setVisible(true);
            } else {
                if (Arrays.stream(timeBase).noneMatch(s -> s == timeInMillis)) {
                    maximumReached.setVisible(false);
                    timeBase[index] = timeInMillis;
                    index++;
                    dlm.add(dlm.getSize(), millisToString(timeInMillis, format));
                    validate();
                } else {
                    if (timeInMillis != 0) {
                        maximumReached.setText("Item is already in the list");
                        maximumReached.setVisible(true);
                    }
                }
            }
        };

        JButton toTimeBase = new JButton("V V V");
        toTimeBase.setBounds(520, 330, 120, 30);
        toTimeBase.addActionListener(writeToTimeBase);
        f.add(toTimeBase);

        String[] ascDesc = new String[]{"Ascending", "Descending"};
        JComboBox<? extends String> sort = new JComboBox<>(ascDesc);
        sort.setBounds(820, 330, 100, 30);
        f.add(sort);


        ActionListener sorting = e -> {
            double[] buffer = new double[13];
            double[] sortBuffer = new double[index];
            if (sort.getSelectedItem().toString().equals("Ascending")) {
                if (index >= 0) System.arraycopy(timeBase, 0, sortBuffer, 0, index);
                Arrays.sort(sortBuffer);
                if (index >= 0) System.arraycopy(sortBuffer, 0, timeBase, 0, index);
                dlm.clear();
                for (double v : timeBase) {
                    if (v != 0)
                        dlm.add(dlm.getSize(), millisToString(v, format));
                }
                validate();
            }
            if (sort.getSelectedItem().toString().equals("Descending")) {
                Arrays.sort(timeBase);
                for (int i = 0; i < timeBase.length; i++) {
                    buffer[i] = timeBase[timeBase.length - 1 - i];
                }
                timeBase = buffer;
                dlm.clear();
                for (double v : timeBase) {
                    if (v != 0)
                        dlm.add(dlm.getSize(), millisToString(v, format));
                }
                validate();
            }
        };
        sort.addActionListener(sorting);

        JList<String> timeList = new JList<>(dlm);
        timeList.setBounds(520, 360, 400, 240);
        f.add(timeList);

        ActionListener differenceOne = e -> {
            int indexOne = timeList.getSelectedIndex();
            if (indexOne != -1) {
                differenceTimeOne = timeBase[indexOne];
                differenceInputOne.setText(dlm.get(indexOne));
            } else {
                differenceInputOne.setText("Пожалуйста, выберите что-нибудь из списка справа");
            }
        };

        ActionListener differenceTwo = e -> {
            int indexTwo = timeList.getSelectedIndex();
            if (indexTwo != -1) {
                differenceTimeTwo = timeBase[indexTwo];
                differenceInputTwo.setText(dlm.get(indexTwo));
            } else {
                differenceInputTwo.setText("Пожалуйста, выберите что-нибудь из списка справа");
            }
        };

        ActionListener toCalculate = e -> {
            int index = timeList.getSelectedIndex();
            if (index != -1) {
                calculateTime = timeBase[index];
                calculatorInputOne.setText(dlm.get(index));
            } else {
                calculatorInputOne.setText("Пожалуйста, выберите что-нибудь из списка справа");
            }
        };

        JButton setToCalculateOne = new JButton("< < <");
        setToCalculateOne.setBounds(425, 360, 90, 30);
        setToCalculateOne.addActionListener(differenceOne);
        f.add(setToCalculateOne);

        JButton setToCalculateTwo = new JButton("< < <");
        setToCalculateTwo.setBounds(425, 390, 90, 30);
        setToCalculateTwo.addActionListener(differenceTwo);
        f.add(setToCalculateTwo);

        JButton setToCalculate = new JButton("< < <");
        setToCalculate.setBounds(425, 510, 90, 30);
        setToCalculate.addActionListener(toCalculate);
        f.add(setToCalculate);

        ActionListener refresh = e -> {
            if (yearFirstOut.isSelected() && yearLastOut.isSelected()
                    || monthFirstOut.isSelected() && monthLastOut.isSelected()
                    || dayFirstOut.isSelected() && dayLastOut.isSelected()) {
                testOut.setText("First and Last должны отличаться!");
                testOut.setVisible(true);
                return;
            }
            if (!hourShowOut.isSelected()) {
                if (minuteShowOut.isSelected()) {
                    testOut.setText("Время без часов, но с минутами? Серьёзно?");
                    testOut.setVisible(true);
                    return;
                }
                if (secondShowOut.isSelected()) {
                    testOut.setText("Время без часов, но с секундами? Серьёзно?");
                    testOut.setVisible(true);
                    return;
                }
                if (millisShowOut.isSelected()) {
                    testOut.setText("Время без часов, но с миллисекундами? Серьёзно?");
                    testOut.setVisible(true);
                    return;
                }
            }
            if (hourShowOut.isSelected() && !minuteShowOut.isSelected()) {
                if (secondShowOut.isSelected()) {
                    testOut.setText("Часы:секунды, серьёзно? Кому нужен такой формат времени?");
                    testOut.setVisible(true);
                    return;
                }
                if (millisShowOut.isSelected()) {
                    testOut.setText("Часы:миллисекунды, серьёзно? Кому нужен такой формат времени?");
                    testOut.setVisible(true);
                    return;
                }
            }
            if (hourShowOut.isSelected() && minuteShowOut.isSelected() && !secondShowOut.isSelected()) {
                if (millisShowOut.isSelected()) {
                    testOut.setText("Часы:минуты:миллисекунды, серьёзно? Кому нужен такой формат времени?");
                    testOut.setVisible(true);
                    return;
                }
            }

            boolean yy12 = yyOut.isSelected();
            boolean yyyy12 = yyyyOut.isSelected();
            boolean m12 = mOut.isSelected();
            boolean mm12 = mmOut.isSelected();
            boolean mmm12 = mmmOut.isSelected();
            boolean d12 = dOut.isSelected();
            boolean dd12 = ddOut.isSelected();
            boolean yearFirst12 = yearFirstOut.isSelected();
            boolean yearLast12 = yearLastOut.isSelected();
            boolean monthFirst12 = monthFirstOut.isSelected();
            boolean monthLast12 = monthLastOut.isSelected();
            boolean dayFirst12 = dayFirstOut.isSelected();
            boolean dayLast12 = dayLastOut.isSelected();
            boolean slash12 = slashOut.isSelected();
            boolean minus12 = minusOut.isSelected();
            boolean dateFirst = dateFirstButtonOut.isSelected();
            boolean timeFirst = timeFirstButtonOut.isSelected();
            boolean hour12 = hourShowOut.isSelected();
            boolean minute12 = minuteShowOut.isSelected();
            boolean second12 = secondShowOut.isSelected();
            boolean millis13 = millisShowOut.isSelected();

            format = new boolean[]{yy12, yyyy12, m12, mm12, mmm12, d12, dd12,
                    yearFirst12, yearLast12, monthFirst12, monthLast12, dayFirst12, dayLast12,
                    slash12, minus12, dateFirst, timeFirst, hour12, minute12, second12, millis13};

            testOut.setText(millisToString(timeInMillis, format));

            dlm.clear();
            for (double v : timeBase) {
                if (v != 0)
                    dlm.add(dlm.getSize(), millisToString(v, format));
            }
            validate();

            if (differenceTimeOne != 0) {
                differenceInputOne.setText(millisToString(differenceTimeOne, format));
            }
            if (differenceTimeTwo != 0) {
                differenceInputTwo.setText(millisToString(differenceTimeTwo, format));
            }
            if (calculateTime != 0) {
                calculatorInputOne.setText(millisToString(calculateTime, format));
            }
            if (calculateResult != 0) {
                resultText.setText(millisToString(calculateResult, format));
            }

        };

        JButton refreshFormat = new JButton("REFRESH");
        refreshFormat.setBounds(775, 250, 145, 30);
        refreshFormat.addActionListener(refresh);
        f.add(refreshFormat);

        f.setSize(950, 655);
        f.setLocation(150, 50);
        f.setResizable(false);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new ExceptionMain();
    }

    public static String millisToString(double time, boolean[] format) {
        boolean yy = format[0];
        boolean yyyy = format[1];
        boolean m = format[2];
        boolean mm = format[3];
        boolean mmm = format[4];
        boolean d = format[5];
        boolean dd = format[6];
        boolean yearFirst = format[7];
        boolean yearLast = format[8];
        boolean monthFirst = format[9];
        boolean monthLast = format[10];
        boolean dayFirst = format[11];
        boolean dayLast = format[12];
        boolean slash = format[13];
        boolean minus = format[14];
        boolean dateFirst = format[15];
        boolean timeFirst = format[16];
        boolean hour = format[17];
        boolean minute = format[18];
        boolean second = format[19];
        boolean millis = format[20];

        StringBuilder output = new StringBuilder();
        String[] outString = new String[7];
        String[] dateOutString = new String[3];
        String[] timeOutString = new String[4];

        if (yearFirst) {
            if (yy) dateOutString[0] = Time.yearYy(Time.year(time));
            if (yyyy) dateOutString[0] = String.valueOf(Time.year(time));

        }
        if (monthFirst) {
            if (m) dateOutString[0] = String.valueOf(Time.month(time));
            if (mm) dateOutString[0] = Time.format(Time.month(time));
            if (mmm) dateOutString[0] = Time.monthMmm(Time.month(time));
        }
        if (dayFirst) {
            if (d) dateOutString[0] = String.valueOf(Time.day(time));
            if (dd) dateOutString[0] = Time.format(Time.day(time));
        }
        if (yearLast) {
            if (yy) dateOutString[2] = Time.yearYy(Time.year(time));
            if (yyyy) dateOutString[2] = String.valueOf(Time.year(time));
        }
        if (monthLast) {
            if (m) dateOutString[2] = String.valueOf(Time.month(time));
            if (mm) dateOutString[2] = Time.format(Time.month(time));
            if (mmm) dateOutString[2] = Time.monthMmm(Time.month(time));
        }
        if (dayLast) {
            if (d) dateOutString[2] = String.valueOf(Time.day(time));
            if (dd) dateOutString[2] = Time.format(Time.day(time));
        }

        if (!yearFirst && !yearLast && yy)
            dateOutString[1] = Time.yearYy(Time.year(time));
        if (!yearFirst && !yearLast && yyyy)
            dateOutString[1] = String.valueOf(Time.year(time));

        if (!monthFirst && !monthLast && m)
            dateOutString[1] = String.valueOf(Time.month(time));
        if (!monthFirst && !monthLast && mm)
            dateOutString[1] = Time.format(Time.month(time));
        if (!monthFirst && !monthLast && mmm)
            dateOutString[1] = Time.monthMmm(Time.month(time));

        if (!dayFirst && !dayLast && d)
            dateOutString[1] = String.valueOf(Time.day(time));
        if (!dayFirst && !dayLast && dd)
            dateOutString[1] = Time.format(Time.day(time));

        int timeSelected = 0;
        if (hour) {
            timeOutString[0] = Time.format(Time.hour(time));
            timeSelected++;
        } else {
            timeOutString[0] = "0";
        }
        if (minute) {
            timeOutString[1] = Time.format(Time.minute(time));
            timeSelected++;
        } else {
            timeOutString[1] = "0";
        }
        if (second) {
            timeOutString[2] = Time.format(Time.second(time));
            timeSelected++;
        } else {
            timeOutString[2] = "0";
        }
        if (millis) {
            timeOutString[3] = Time.formatMillis(Time.millis(time));
            timeSelected++;
        } else {
            timeOutString[3] = "0";
        }

        if (dateFirst) {
            System.arraycopy(dateOutString, 0, outString, 0, dateOutString.length);
            System.arraycopy(timeOutString, 0, outString, 3, timeSelected);
        }

        if (timeFirst) {
            System.arraycopy(timeOutString, 0, outString, 0, timeSelected);
            System.arraycopy(dateOutString, 0, outString, timeSelected, dateOutString.length);
        }

        String separator = null;
        if (slash) separator = "/";
        if (minus) separator = "-";

        for (int i = 0; i < dateOutString.length + timeSelected; i++) {
            output.append(outString[i]);
            if (dateFirst) {
                if (i == 2) output.append(" ");
                if (i == 0 || i == 1) {
                    output.append(separator);
                }
                if (timeSelected == 2 && i == 3) output.append(":");
                if (timeSelected == 3 && (i == 3 || i == 4)) output.append(":");
                if (timeSelected == 4 && (i == 3 || i == 4 || i == 5)) output.append(":");
            }

            if (timeFirst) {
                if (i == timeSelected - 1) output.append(" ");
                if (i < timeSelected - 1) output.append(":");
                if (i > timeSelected - 1 && i <= timeSelected + 1) output.append(separator);
            }
        }
        return output.toString();
    }

    public static boolean isInputYearOk(String year, String mode) {
        if (year.isEmpty()) return true;
        try {
            Integer.parseInt(year);
        } catch (NumberFormatException e) {
            if (e != null) {
                return false;
            }
        }
        if (isNotNumber(year)) return false;
        if (mode.equals("yyyy") && Integer.parseInt(year) > 9999) return false;
        if (mode.equals("yy") && Integer.parseInt(year) > 99) return false;
        return true;
    }

    public static boolean isInputMonthOk(String month, String mode) {
        String[] months = new String[]{
                "Январь", "Февраль", "Март",
                "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь",
                "Октябрь", "Ноябрь", "Декабрь"};
        if (month.isEmpty()) return true;
        try {
            Integer.parseInt(month);
        } catch (NumberFormatException e) {
            if (e != null) {
                return false;
            }
        }
        if (mode.equals("mmm") && !Arrays.asList(months).contains(month)) return false;
        if ((mode.equals("m") || mode.equals("mm")) &&
                (isNotNumber(month) || Integer.parseInt(month) > 12 || Integer.parseInt(month) == 0)) return false;
        if (mode.equals("m") && month.indexOf("0") == 0) return false;
        if (mode.equals("mm") && Integer.parseInt(month) < 10 && month.indexOf("0") != 0) return false;
        return true;
    }

    public static boolean isInputDayOk(String day, int month, int year, String mode) {
        if (day.isEmpty()) return true;
        try {
            Integer.parseInt(day);
        } catch (NumberFormatException e) {
            if (e != null) {
                return false;
            }
        }
        if (isNotNumber(day)) return false;
        if (mode.equals("d") && day.indexOf("0") == 0) return false;
        int dayInt = Integer.parseInt(day);
        if (mode.equals("dd") && dayInt < 10 && day.indexOf("0") != 0) return false;
        if (dayInt > 31) return false;
        if (month == 1 && dayInt > 31) return false;
        if (month == 2 && ((dayInt > 28 && !Time.leap(year))
                || (dayInt > 29 && Time.leap(year)))) return false;
        if (month == 3 && dayInt > 31) return false;
        if (month == 4 && dayInt > 30) return false;
        if (month == 5 && dayInt > 31) return false;
        if (month == 6 && dayInt > 30) return false;
        if (month == 7 && dayInt > 31) return false;
        if (month == 8 && dayInt > 31) return false;
        if (month == 9 && dayInt > 30) return false;
        if (month == 10 && dayInt > 31) return false;
        if (month == 11 && dayInt > 30) return false;
        if (month == 12 && dayInt > 31) return false;
        return true;
    }

    public static boolean isInputHourOk(String hour) {
        if (hour.isEmpty()) return true;
        try {
            Integer.parseInt(hour);
        } catch (NumberFormatException e) {
            if (e != null) {
                return false;
            }
        }
        if (isNotNumber(hour)) return false;
        if (Integer.parseInt(hour) > 23) return false;
        return true;
    }

    public static boolean isInputMinuteOk(String minute) {
        if (minute.isEmpty()) return true;
        try {
            Integer.parseInt(minute);
        } catch (NumberFormatException e) {
            if (e != null) {
                return false;
            }
        }
        if (isNotNumber(minute)) return false;
        if (Integer.parseInt(minute) > 59) return false;
        return true;
    }

    public static boolean isInputSecondOk(String second) {
        if (second.isEmpty()) return true;
        try {
            Integer.parseInt(second);
        } catch (NumberFormatException e) {
            if (e != null) {
                return false;
            }
        }
        if (isNotNumber(second)) return false;
        if (Integer.parseInt(second) > 59) return false;
        return true;
    }

    public static boolean isInputMillisOk(String millis) {
        if (millis.isEmpty()) return true;
        try {
            Integer.parseInt(millis);
        } catch (NumberFormatException e) {
            if (e != null) {
                return false;
            }
        }
        if (isNotNumber(millis)) return false;
        if (Integer.parseInt(millis) > 999) return false;
        return true;
    }

    public static boolean isNotNumber(String input) {
        char[] array = input.toCharArray();
        String allowed = "0123456789";
        boolean result = false;
        for (char c : array) {
            if (allowed.indexOf(c) == -1) {
                result = true;
                break;
            }
        }
        return result;
    }
}
