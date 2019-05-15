//
//  DetailViewController.swift
//  ProjectFinal
//
//  Created by Jordan Denning on 3/8/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {
    var newWine = favWineController()
//    var favWineList = [WineList]()
    
    @IBAction func favorite(_ sender: Any) {
        
        let alert=UIAlertController(title: "Favorite", message: "Add wine to favorites?", preferredStyle: UIAlertController.Style.alert)
        //create a UIAlertAction object for the button
        let cancelAction=UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel, handler: nil)
        alert.addAction(cancelAction) //adds the alert action to the alert object
        let okAction=UIAlertAction(title: "Add", style: .default, handler:{action in
            self.newWine.addWine(name: self.title!, region: self.regionLabel.text!, winery: self.wineryLabel.text!, varietal: self.varietalLabel.text!, price: self.priceLabel.text!, vintage: self.vintageLabel.text!)

        })
        alert.addAction(okAction)
        alert.view.tintColor = UIColor.red
        present(alert, animated: true, completion: nil)
    }
    var detailItem: String = ""
    @IBOutlet weak var regionLabel: UILabel!
    @IBOutlet weak var wineryLabel: UILabel!
    @IBOutlet weak var varietalLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var vintageLabel: UILabel!
    var labelName: String?
    var labelRegion: String?
    var labelPrice: String?
    var labelWinery: String?
    var labelVintage: String?
    var labelVarietal: String?
    
    func configureView() {
        // Update the user interface for the detail item.

        if let regionDetail = labelRegion{
            if let label = regionLabel{
                label.text = regionDetail.description
            }
        }
        
        if let wineryDetail = labelWinery{
            if let label = wineryLabel{
                label.text = wineryDetail.description
            }
        }
        
        if let varietalDetail = labelVarietal{
            if let label = varietalLabel{
                label.text = varietalDetail.description
            }
        }
        
        if let vintageDetail = labelVintage{
            if let label = vintageLabel{
                label.text = vintageDetail.description
            }
        }
        
        if let priceDetail = labelPrice{
            if let label = priceLabel{
                label.text = "$" + priceDetail.description
            }
        }
        
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        newWine.setupFirebaseListener()
        // Do any additional setup after loading the view, typically from a nib.
        configureView()
    }
    
    
    
    
}

