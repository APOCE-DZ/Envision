package com.example.envision.brestcancer.model;

import java.io.Serializable;

public class Patient implements Serializable {
        long id;
        String name;
        String blood_group;
        double radius_mean;
        double texture_mean;
        double perimeter_mean;
        double area_mean;
        double smoothness_mean;
        double compactness_mean;
        double concavity_mean;
        double concave_points_mean;
        double symmetry_mean;
        double fractal_dimension_mean;
        double radius_se;
        double texture_se;
        double perimeter_se;
        double area_se;
        double smoothness_se;
        double compactness_se;
        double concavity_se;
        double concave_points_se;
        double symmetry_se;
        double fractal_dimension_se;
        double radius_worst;
        double texture_worst;
        double perimeter_worst;
        double area_worst;
        double smoothness_worst;
        double compactness_worst;
        double concavity_worst;
        double concave_points_worst;
        double symmetry_worst;
        double fractal_dimension_worst;
        int diagnosis;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getBlood_group() {
                return blood_group;
        }

        public void setBlood_group(String blood_group) {
                this.blood_group = blood_group;
        }

        public double getRadius_mean() {
                return radius_mean;
        }

        public void setRadius_mean(double radius_mean) {
                this.radius_mean = radius_mean;
        }

        public double getTexture_mean() {
                return texture_mean;
        }

        public void setTexture_mean(double texture_mean) {
                this.texture_mean = texture_mean;
        }

        public double getPerimeter_mean() {
                return perimeter_mean;
        }

        public void setPerimeter_mean(double perimeter_mean) {
                this.perimeter_mean = perimeter_mean;
        }

        public double getArea_mean() {
                return area_mean;
        }

        public void setArea_mean(double area_mean) {
                this.area_mean = area_mean;
        }

        public double getSmoothness_mean() {
                return smoothness_mean;
        }

        public void setSmoothness_mean(double smoothness_mean) {
                this.smoothness_mean = smoothness_mean;
        }

        public double getCompactness_mean() {
                return compactness_mean;
        }

        public void setCompactness_mean(double compactness_mean) {
                this.compactness_mean = compactness_mean;
        }

        public double getConcavity_mean() {
                return concavity_mean;
        }

        public void setConcavity_mean(double concavity_mean) {
                this.concavity_mean = concavity_mean;
        }


        public double getSymmetry_worst() {
                return symmetry_worst;
        }

        public void setSymmetry_worst(double symmetry_worst) {
                this.symmetry_worst = symmetry_worst;
        }

        public double getFractal_dimension_worst() {
                return fractal_dimension_worst;
        }

        public void setFractal_dimension_worst(double fractal_dimension_worst) {
                this.fractal_dimension_worst = fractal_dimension_worst;
        }

        public int getDiagnosis() {
                return diagnosis;
        }

        public void setDiagnosis(int diagnosis) {
                this.diagnosis = diagnosis;
        }

        public double getSymmetry_se() {
                return symmetry_se;
        }

        public void setSymmetry_se(double symmetry_se) {
                this.symmetry_se = symmetry_se;
        }

        public double getFractal_dimension_se() {
                return fractal_dimension_se;
        }

        public void setFractal_dimension_se(double fractal_dimension_se) {
                this.fractal_dimension_se = fractal_dimension_se;
        }

        public double getRadius_worst() {
                return radius_worst;
        }

        public void setRadius_worst(double radius_worst) {
                this.radius_worst = radius_worst;
        }

        public double getTexture_worst() {
                return texture_worst;
        }

        public void setTexture_worst(double texture_worst) {
                this.texture_worst = texture_worst;
        }

        public double getPerimeter_worst() {
                return perimeter_worst;
        }

        public void setPerimeter_worst(double perimeter_worst) {
                this.perimeter_worst = perimeter_worst;
        }

        public double getArea_worst() {
                return area_worst;
        }

        public void setArea_worst(double area_worst) {
                this.area_worst = area_worst;
        }

        public double getSmoothness_worst() {
                return smoothness_worst;
        }

        public void setSmoothness_worst(double smoothness_worst) {
                this.smoothness_worst = smoothness_worst;
        }

        public double getCompactness_worst() {
                return compactness_worst;
        }

        public void setCompactness_worst(double compactness_worst) {
                this.compactness_worst = compactness_worst;
        }

        public double getConcavity_worst() {
                return concavity_worst;
        }

        public void setConcavity_worst(double concavity_worst) {
                this.concavity_worst = concavity_worst;
        }

        public double getSymmetry_mean() {
                return symmetry_mean;
        }

        public void setSymmetry_mean(double symmetry_mean) {
                this.symmetry_mean = symmetry_mean;
        }

        public double getFractal_dimension_mean() {
                return fractal_dimension_mean;
        }

        public void setFractal_dimension_mean(double fractal_dimension_mean) {
                this.fractal_dimension_mean = fractal_dimension_mean;
        }

        public double getRadius_se() {
                return radius_se;
        }

        public void setRadius_se(double radius_se) {
                this.radius_se = radius_se;
        }

        public double getTexture_se() {
                return texture_se;
        }

        public void setTexture_se(double texture_se) {
                this.texture_se = texture_se;
        }

        public double getPerimeter_se() {
                return perimeter_se;
        }

        public void setPerimeter_se(double perimeter_se) {
                this.perimeter_se = perimeter_se;
        }

        public double getArea_se() {
                return area_se;
        }

        public void setArea_se(double area_se) {
                this.area_se = area_se;
        }

        public double getSmoothness_se() {
                return smoothness_se;
        }

        public void setSmoothness_se(double smoothness_se) {
                this.smoothness_se = smoothness_se;
        }

        public double getCompactness_se() {
                return compactness_se;
        }

        public void setCompactness_se(double compactness_se) {
                this.compactness_se = compactness_se;
        }

        public double getConcavity_se() {
                return concavity_se;
        }

        public void setConcavity_se(double concavity_se) {
                this.concavity_se = concavity_se;
        }

        public double getConcave_points_mean() {
                return concave_points_mean;
        }

        public void setConcave_points_mean(double concave_points_mean) {
                this.concave_points_mean = concave_points_mean;
        }

        public double getConcave_points_se() {
                return concave_points_se;
        }

        public void setConcave_points_se(double concave_points_se) {
                this.concave_points_se = concave_points_se;
        }

        public double getConcave_points_worst() {
                return concave_points_worst;
        }

        public void setConcave_points_worst(double concave_points_worst) {
                this.concave_points_worst = concave_points_worst;
        }


}