//
//  ViewController.swift
//  ProjectFinal
//
//  Created by Jordan Denning on 3/8/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {

    /*https://stackoverflow.com/questions/12905568/how-to-use-uiscrollview-in-storyboard used this to make my scroll views*/
    
    let redWineList = ["Cabernet Sauvignon","Malbec","Merlot","Pinot Noir","Sangiovese","Shiraz","Zinfandel","Barbera"]
    let whiteWineList = ["Chenin Blanc","Pinot Grigio","Riesling","Sauvignon Blanc", "Chardonnay", "Gewurztraminer", "Muscat", "Semillon"]
    let roseWineList = ["Grenache","Syrah","Tempranillo","Pinot Noir","Tavel","Sangiovese","White Zinfandel"]

    @IBOutlet weak var winePicker: UIPickerView!
    
    var type = String()
    var variety = String()
    @IBOutlet weak var wineSegment: UISegmentedControl!
    @IBAction func switchWine(_ sender: UISegmentedControl) {
        if wineSegment.selectedSegmentIndex == 0
        {
            type = "red"
            variety = redWineList[0]
            
        }
        else if wineSegment.selectedSegmentIndex == 1
        {
            type = "white"
            variety = whiteWineList[0]
        }
        else
        {
            type = "rose"
            variety = roseWineList[0]
            
            
        }
        self.winePicker.reloadComponent(0)
        winePicker.selectRow(0, inComponent: 0, animated: true)
        print(variety)
    }
    /*https://stackoverflow.com/questions/11191062/how-to-reset-uipickerview-to-index0-iphone/11191157 reset picker view*/
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if type == "red"
        {
            return redWineList[row]
        }
        else if type == "white"{
            return whiteWineList[row]
        }
        else
        {
            return roseWineList[row]
        }
    }
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if type == "red"
        {
            return redWineList.count
        }
        else if type == "white"{
            return whiteWineList.count
        }
        else
        {
            return roseWineList.count
        }
    }
    
        func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
            if type == "red"
            {
                variety = redWineList[row]
            }
            else if type == "white"{
                variety =  whiteWineList[row]
            }
            else
            {
                variety =  roseWineList[row]
            }
//            print(variety)
        }
    
    

    
    /*https://codewithchris.com/uipickerview-example/  this is what told me to do the self thing and it's what made the picker work*/
    override func viewDidLoad() {
        super.viewDidLoad()
        type = "red"
        variety = redWineList[0]
        self.winePicker.delegate = self
        self.winePicker.dataSource = self
        print(type, variety)
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showWines" {
            let controller = segue.destination as! CollectionViewController
            controller.wineType = type
            controller.wineVariety = variety
//            print(controller.wineVariety)
            controller.navigationItem.leftItemsSupplementBackButton = true
            }
        }


}

