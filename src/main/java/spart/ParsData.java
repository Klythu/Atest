package spart;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ParsData {

    public HashMap<String, Object> parsInputData() {
        InputData inputData = new InputData();
        String[] data = inputData.enterData();
        HashMap<String, Object> dataDic = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (String i : data) {
            if (i.length() == 1) {
                if (i.equals("f") || i.equals("m")) {
                    dataDic.put("sex", i);
                } else{
                    try {
                        throw new SexException();
                    } catch (SexException e) {
                        e.sexException(i);
                    }
                }
            } else if (i.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}")) {
                String[] arrayDate = i.split("\\.");
                Date DateNow=new Date();
                SimpleDateFormat formatForDateNowYear = new SimpleDateFormat("yyyy");
                SimpleDateFormat formatForDateNowMonth = new SimpleDateFormat("MM");
                SimpleDateFormat formatForDateNowDay = new SimpleDateFormat("dd");
                boolean flag = false;
                if (Integer.parseInt(arrayDate[0]) < 0 ||
                        Integer.parseInt(arrayDate[0]) > 31 || Integer.parseInt(arrayDate[1]) < 0 ||
                        Integer.parseInt(arrayDate[1]) > 12 || Integer.parseInt(arrayDate[2]) < 0 ||
                        Integer.parseInt(arrayDate[2]) > Integer.parseInt(formatForDateNowYear.format(DateNow))) {
                    try {
                        throw new DateException();
                    } catch (DateException e) {
                        e.dataException(i);
                        System.out.println(formatForDateNowYear.format(DateNow));
                    }
                } else if (Integer.parseInt(arrayDate[2]) == Integer.parseInt(formatForDateNowYear.format(DateNow))
                        & Integer.parseInt(arrayDate[1]) > Integer.parseInt(formatForDateNowMonth.format(DateNow))) {
                    try {
                        throw new FutureException();
                    } catch (FutureException e) {
                        e.futureException(i);
                        System.out.println(Integer.parseInt(arrayDate[1]) > Integer.parseInt(formatForDateNowMonth.format(DateNow)));
                    }

                } else if (Integer.parseInt(arrayDate[2]) == Integer.parseInt(formatForDateNowYear.format(DateNow))
                        & Integer.parseInt(arrayDate[1]) == Integer.parseInt(formatForDateNowMonth.format(DateNow))
                        & Integer.parseInt(arrayDate[0]) > Integer.parseInt(formatForDateNowDay.format(DateNow))) {
                    try {
                        throw new FutureException();
                    } catch (FutureException e) {
                        e.futureException(i);
                        System.out.println(formatForDateNowDay.format(DateNow));
                    }
                } else {
                    if (Integer.parseInt(arrayDate[2]) % 4 == 0) {
                        if (Integer.parseInt(arrayDate[1]) == 1 ||
                                Integer.parseInt(arrayDate[1]) == 3 ||
                                Integer.parseInt(arrayDate[1]) == 5 ||
                                Integer.parseInt(arrayDate[1]) == 7 ||
                                Integer.parseInt(arrayDate[1]) == 9 ||
                                Integer.parseInt(arrayDate[1]) == 10 ||
                                Integer.parseInt(arrayDate[1]) == 12) {
                            if (Integer.parseInt(arrayDate[0]) < 32) {
                                flag = true;
                            }
                        } else if (Integer.parseInt(arrayDate[1]) == 2) {
                            if (Integer.parseInt(arrayDate[0]) < 30) {
                                flag = true;
                            }
                        } else if (Integer.parseInt(arrayDate[1]) == 4 ||
                                Integer.parseInt(arrayDate[1]) == 6 ||
                                Integer.parseInt(arrayDate[1]) == 8 ||
                                Integer.parseInt(arrayDate[1]) == 11) {
                            if (Integer.parseInt(arrayDate[0]) < 31) {
                                flag = true;
                            }
                        }
                    } else {
                        if (Integer.parseInt(arrayDate[1]) == 1 ||
                                Integer.parseInt(arrayDate[1]) == 3 ||
                                Integer.parseInt(arrayDate[1]) == 5 ||
                                Integer.parseInt(arrayDate[1]) == 7 ||
                                Integer.parseInt(arrayDate[1]) == 9 ||
                                Integer.parseInt(arrayDate[1]) == 10 ||
                                Integer.parseInt(arrayDate[1]) == 12) {
                            if (Integer.parseInt(arrayDate[0]) < 32) {
                                flag = true;
                            }
                        } else if (Integer.parseInt(arrayDate[2]) == 2) {
                            if (Integer.parseInt(arrayDate[0]) < 29) {
                                flag = true;
                            }
                        } else if (Integer.parseInt(arrayDate[1]) == 4 ||
                                Integer.parseInt(arrayDate[1]) == 6 ||
                                Integer.parseInt(arrayDate[1]) == 8 ||
                                Integer.parseInt(arrayDate[1]) == 11) {
                            if (Integer.parseInt(arrayDate[0]) < 31) {
                                flag = true;
                            }
                        }

                    }
                    if (flag) {
                        dataDic.put("date", i);
                    } else try {
                        throw new DataException();
                    } catch (DataException e) {
                        e.dataException(i);
                    }
                }

            } else if (i.matches("[0-9]+")) {
                dataDic.put("tel", i);
            } else if (i.matches("[A-Za-z]+")) {
                sb.append(i + " ");
            } else {
                try {
                    throw new DataException();
                } catch (DataException e) {
                    e.dataException(i);
                }
            }
        }
        String[] fullName = String.valueOf(sb).split(" ");
        if (fullName.length == 3) {
            dataDic.put("firstName", fullName[1]);
            dataDic.put("lastName", fullName[0]);
            dataDic.put("patronymic", fullName[2]);
        }
        return dataDic;
    }

}
