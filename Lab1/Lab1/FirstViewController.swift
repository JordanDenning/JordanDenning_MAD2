//
//  FirstViewController.swift
//  Lab1
//
//  Created by Jordan Denning on 2/2/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController, UIPickerViewDelegate,UIPickerViewDataSource {

    @IBOutlet weak var dependentPicker: UIPickerView!
    @IBOutlet weak var choiceLabel: UILabel!
    
    let plasticComponent = 0
    let useComponent = 1
    
    var plasticTypes = PlasticUsesController()
    var plastic = [String]()
    var use = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        plasticTypes.loadData()
        plastic = plasticTypes.getPlastic()
        use = plasticTypes.getUse(index: 0)
        choiceLabel.text = "The plastic \(plastic[0]) is used to make \(use[0])."
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == plasticComponent{
            return plastic.count
        }
        else{
            return use.count
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == plasticComponent{
            return plastic[row]
        }
        else{
            return use[row]
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == plasticComponent{
            use = plasticTypes.getUse(index: row)
            dependentPicker.reloadComponent(1)
            dependentPicker.selectRow(0, inComponent: 1, animated: true)
        }
        
        let plasticrow = dependentPicker.selectedRow(inComponent: 0)
        let userow = dependentPicker.selectedRow(inComponent: 1)
        
        choiceLabel.text = "The plastic \(plastic[plasticrow]) is used to make \(use[userow])."
    }
    



}

