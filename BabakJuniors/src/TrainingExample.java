import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @variables
 * -exampleName : String : It holds the name of the TrainingExample.
 * -feature :HashMap<String,Feature> : in Hashmap, it will hold Key : name of type of the Features, Value : feature  
 * -nameSet :DefaultListModel<String> : This contains all the names of type of the feature.
 * @Method
 * +addFeature(String keyname,Feature value) : adding feature with type of the fature in hashmap.
 * +getFeature(String keyname) : feature : getter for the Feature using keyname
 * +removeFeature(String keyname) : remove the feature from the hash map
 * +getExampleName() : String : getter for name of the Training example 
 * +editFeature(String keyname, Feature value) : editing the Feature with other feature. 
 * +editExampleName(String name) : editing the examplename.
 * +checkKeyName(String keyname) : boolean : it returns true or false, if hashmap contains the key. 
 * +getKeyName(Feature value) : String : getter for key from the hashmap.  
 * +getNameSet() : DefaultListModel<String> : it returns DefaultListmodel of String containing all the name of key.
 * +getAllFeatures() : HashMap<String,Feature> : it return the hash map for all the features.
 * +setFeatures(HashMap<String, Feature> h) : setter for hashmap features.
 * +getTrainingExampleName() : String : getter for Training Example Name;
 * +setTrainingExampleName(String name) : setter for TrainingExample name;
 * + toString() : String : it returns String containing all the feature name and feature type;
 *
 */


public class TrainingExample {
	private String exampleName;
	private Example manager;
	private CompositeFeature features; 
	private ArrayList<Feature> linearizedFeatures;
	private DefaultListModel<String>printFeatures;
	
	public TrainingExample() {
		exampleName=null;
		manager=null;
		features=null;
	}
	
	public TrainingExample(String name){
		//this.manager=manager;
		printFeatures = new DefaultListModel<String>();
		features = new CompositeFeature("head");
		this.exampleName = name; 
	}
	
	public TrainingExample(String name, Example man){
		this.manager=man;
		printFeatures = new DefaultListModel<String>();
		features = new CompositeFeature("head");
		this.exampleName = name; 
	}
	

	public DefaultListModel<String> getPrintFeatures(){
		printFeatures.addElement(toString());
		return printFeatures;
	}
	
	public String getTrainingExampleName(){
		return exampleName;
	}
	public void setTrainingExampleName(String name){
		exampleName = name;
	}
	
	public CompositeFeature addStringFeature(String fName,String value,CompositeFeature  currentFeature) {
		StringFeature sFeature = new StringFeature(fName,value);
		currentFeature.addFeature(sFeature);
		return currentFeature;
	}
	//2
	public CompositeFeature addFloatFeature(String fName,Float value,CompositeFeature currentFeature) {
		FloatFeature fFeature = new FloatFeature(fName,value);
		currentFeature.addFeature(fFeature);
		return currentFeature;
	}
	//
	//3
	public CompositeFeature addCompositeFeature(String compositeName,CompositeFeature currentFeature) {
		CompositeFeature comp = new CompositeFeature(compositeName);
		currentFeature.addFeature(comp);
		return currentFeature;
	}
	//case4
	//
	public Feature getFeature(String s) {
		if(s.equals(features.getFName()+"->")) {
			return features;
		}else {
			return getFeature(s,features);
		}
		//
		/*
		for(int i =0;i<features.getSubFeatureSize();i++) {
			if()	
		
		}
		for (Feature f: features.getSubFeatures()) {
			if (f.getStringID("", this.getFeatures()).equals(s)) {
				return f;
			}
			if(f instanceof CompositeFeature) {
				CompositeFeature F =(CompositeFeature)f;
				if(!F.isThereComposite() && F.getStringID("", F).equals(s)) {
					return f;
				}
				f = f.;
			}
			if(f instanceof CompositeFeature) {
				return getFeature(s);
			}
			
			
		}*/
	}
	public Feature getFeature(String s, CompositeFeature features) {
		Feature fe =features;
		for (Feature f: features.getSubFeatures()) {
			if (f.getStringID("", f).equals(s)) {
				fe = f;
			}
			if(f instanceof CompositeFeature) {
				CompositeFeature F =(CompositeFeature)f;
				if(!F.isThereComposite() && F.getStringID("", F).equals(s)) {
					fe = f;
				}else {
					return getFeature(s,F);
				}
			}
		}
		if (features.getStringID("", this.getFeatures()).equals(s)) {
			fe = features;
		}
		return fe;
	}
	public ArrayList<Feature> linearizeFeatures(ArrayList<Feature> list){
		for (Feature f: features.getSubFeatures()) {
			list.add(f);
			if(f instanceof CompositeFeature) {
				linearizeFeatures(list);
			}
		}
		return list;
	}
	public CompositeFeature jumpIn(String compositeName,CompositeFeature currentFeature) throws IOException{
		CompositeFeature newCurrent;
		for(int i=0;i<features.getSubFeatureSize();i++) {
			if(features.getSubFeature(i).getFName().equals(compositeName) && features.getSubFeature(i) instanceof CompositeFeature) {
				newCurrent = (CompositeFeature)features.getSubFeature(i);
				return newCurrent;
			}
		}
		return null;
	}	
	
	public CompositeFeature getFeatures() {
		return features;
	}
	public void setFeatures(CompositeFeature features) {
		this.features = features;
	}
	public CompositeFeature getCompositeFeature() {
		return this.features;
	}
	public Example getManager() {
		return manager;
	}

	
	public String toString(){
		String tostring = exampleName + ": ";
		tostring=tostring+ features.toString();
		return tostring;
	}
}
