//
//  DetailViewController.swift
//  ProjectPrototype
//
//  Created by Jordan Denning on 2/24/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    var detailItem: String?
    @IBOutlet weak var nameLabel: UILabel!
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
        if let nameDetail = labelName {
            if let label = nameLabel{
                label.text = nameDetail.description
            }
        }
        
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
        // Do any additional setup after loading the view, typically from a nib.
        configureView()
    }

    


}

