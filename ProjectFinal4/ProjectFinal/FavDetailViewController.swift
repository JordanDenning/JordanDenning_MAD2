//
//  FavDetailViewController.swift
//  ProjectFinal
//
//  Created by Jordan Denning on 3/10/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class FavDetailViewController: UIViewController {
    
    var wineData = favWineController()
    var id = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureView()

        // Do any additional setup after loading the view.
    }
    

    @IBAction func deleteFav(_ sender: Any) {
        let alert=UIAlertController(title: "Delete", message: "Remove wine from favorites?", preferredStyle: UIAlertController.Style.alert)
        //create a UIAlertAction object for the button
        let cancelAction=UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel, handler: nil)
        alert.addAction(cancelAction) //adds the alert action to the alert object
        
        let okAction=UIAlertAction(title: "Delete", style: .default, handler:{action in
//            let wineid = self.wines[self.index].id
//            print(wineid)
            self.wineData.deleteWine(wineID: self.id)
            
        })
        alert.addAction(okAction)
        alert.view.tintColor = UIColor.red
        present(alert, animated: true, completion: nil)
    }

    @IBOutlet weak var regionLabel: UILabel!
    @IBOutlet weak var wineryLabel: UILabel!
    @IBOutlet weak var varietalLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var vintageLabel: UILabel!
    
    var labelName: String = ""
    var labelRegion: String = ""
    var labelPrice: String = ""
    var labelWinery: String = ""
    var labelVintage: String = ""
    var labelVarietal: String = ""
    
    func configureView() {
        // Update the user interface for the detail item.
        
        regionLabel.text = labelRegion
        wineryLabel.text = labelWinery
        varietalLabel.text = labelVarietal
        priceLabel.text = labelPrice
        vintageLabel.text = labelVintage

}
}
