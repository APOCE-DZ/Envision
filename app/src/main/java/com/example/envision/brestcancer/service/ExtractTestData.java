class ExtractTestData{
    static String breastCancertestData = "BreastCancertestingset.csv";
    AssetManager asset = getAssets();
    public static ArrayList<Patient> extractData(){
        try {
        csvReader = new BufferedReader(new InputStreamReader(asset.open(breastCancertestData), Charset.forName("UTF-8")));
            String row = "";
            csvReader.readLine()
                    int i=0;
            ArrayList<Patient> arrayOfPatient = new ArrayList<Patient>();
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Patient patient=new Patient();
                ArrayList<String> tuple = new ArrayList(Arrays.asList(data));
                patient.setId(Long.parseLong(tuple.get(0)));
                patient.setName(tuple.get(1));
                patient.setBlood_group(tuple.get(2));
                patient.setRadius_mean(Double.parseDouble(tuple.get(3)));
                patient.setTexture_mean(Double.parseDouble(tuple.get(4)));
                patient.setPerimeter_mean(Double.parseDouble(tuple.get(5)));
                patient.setArea_mean(Double.parseDouble(tuple.get(6)));
                patient.setSmoothness_mean(Double.parseDouble(tuple.get(7)));
                patient.setCompactness_mean(Double.parseDouble(tuple.get(8)));
                patient.setConcavity_mean(Double.parseDouble(tuple.get(9)));
                patient.setConcave_points_mean(Double.parseDouble(tuple.get(10)));
                patient.setSymmetry_mean(Double.parseDouble(tuple.get(11)));
                patient.setFractal_dimension_mean(Double.parseDouble(tuple.get(12)));
                patient.setRadius_se(Double.parseDouble(tuple.get(13)));
                patient.setTexture_se(Double.parseDouble(tuple.get(14)));
                patient.setPerimeter_se(Double.parseDouble(tuple.get(15)));
                patient.setArea_se(Double.parseDouble(tuple.get(16)));
                patient.setSmoothness_se(Double.parseDouble(tuple.get(17)));
                patient.setCompactness_se(Double.parseDouble(tuple.get(18)));
                patient.setConcavity_se(Double.parseDouble(tuple.get(19)));
                patient.setConcave_points_se(Double.parseDouble(tuple.get(20)));
                patient.setSymmetry_se(Double.parseDouble(tuple.get(21)));
                patient.setFractal_dimension_se(Double.parseDouble(tuple.get(22)));
                patient.setRadius_worst(Double.parseDouble(tuple.get(23)));
                patient.setTexture_worst(Double.parseDouble(tuple.get(24)));
                patient.setPerimeter_worst(Double.parseDouble(tuple.get(25)));
                patient.setArea_worst(Double.parseDouble(tuple.get(26)));
                patient.setSmoothness_worst(Double.parseDouble(tuple.get(27)));
                patient.setCompactness_worst(Double.parseDouble(tuple.get(28)));
                patient.setConcavity_worst(Double.parseDouble(tuple.get(29)));
                patient.setConcave_points_worst(Double.parseDouble(tuple.get(30)));
                patient.setSymmetry_worst(Double.parseDouble(tuple.get(31)));
                patient.setFractal_dimension_worst(Double.parseDouble(tuple.get(32)));
                patient.setDiagnosis(Integer.parseInt(tuple.get(33)));
                arrayOfPatient.add(patient)
                i++;
                if(i>20){
                    break;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayOfPatient;
    }
}