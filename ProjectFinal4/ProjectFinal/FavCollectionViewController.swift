//
//  FavCollectionViewController.swift
//  ProjectFinal
//
//  Created by Jordan Denning on 3/10/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

private let reuseIdentifier = "Cell"

class FavCollectionViewController: UICollectionViewController, UICollectionViewDelegateFlowLayout {
    
    var wines = [favWine]()
    var wineData = favWineController()
//    var wineType = String()
//    var wineVariety = String()
//    var region = String()
//    var varietal = String()
//    var price = String()
//    var winery = String()
//    var vintage = String()
//    var newName = String()
//    var shortName = String()
//    
    func render(){
        wines = wineData.getWine()
//        print(wines)
        //reload the table data
        collectionView.reloadData()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //assign the closure with the method we want called to the onDataUpdate closure
        wineData.onDataUpdate = {[weak self] (data:[favWine]) in self?.render()}
        //        print("in fav view thing")
        wineData.setupFirebaseListener()
    }
    


    // MARK: UICollectionViewDataSource

    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of items
        return wines.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! FavCollectionViewCell
        
        cell.favLabel.text = wines[indexPath.row].name
        
        cell.layer.cornerRadius = 3.5
        cell.layer.borderWidth = 4.5
        cell.layer.borderColor = UIColor.black.cgColor

        
    
        // Configure the cell
    
        return cell
    }
    
    func collectionView( _ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int)-> UIEdgeInsets {
        let sectionInsets = UIEdgeInsets(top: 2, left: 2.5, bottom: 0, right: 2.5)
        return sectionInsets
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showFavDetail" {
            if let indexPath = collectionView.indexPath(for: sender as! FavCollectionViewCell) {
                let wine = wines[indexPath.row]
                

                
                let controller = segue.destination as! FavDetailViewController
                controller.wineData = wineData
                controller.id = wine.id
                controller.title = wine.name
                controller.labelRegion = wine.region
                print("Region" + wine.region)
                controller.labelPrice = wine.price
                controller.labelWinery = wine.winery
                controller.labelVintage = wine.vintage
                controller.labelVarietal = wine.varietal
                controller.navigationItem.leftItemsSupplementBackButton = true
                
                
                
            }
        }
    }
    

}
