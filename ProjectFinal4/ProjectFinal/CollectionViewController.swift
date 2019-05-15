//
//  CollectionViewController.swift
//  ProjectFinal
//
//  Created by Jordan Denning on 3/10/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import UIKit

private let reuseIdentifier = "Cell"


class CollectionViewController: UICollectionViewController,UICollectionViewDelegateFlowLayout {

    var wineData = WineDataController()
    var wines = [WineList]()
    var wineNames = [String]()
    var searchController = UISearchController()
    var wineType = String()
    var wineVariety = String()
    var region = String()
    var varietal = String()
    var price = String()
    var winery = String()
    var vintage = String()
    var newName = String()
    var shortName = String()
    
    func  render() {
        wines = wineData.getWines()
        for wineType in wines{
            let substring = wineType.name.split(separator: " ")
            if substring.count > 4{
                newName = String(substring[0]) + " " + String(substring[1]) + " " + String(substring[2]) + " " + String(substring[3])
            }
            else{
                newName = wineType.name
            }
            wineNames.append(newName)
        }
//        print(wineNames)
        collectionView.reloadData()
        
        
    }
    override func viewDidLoad() {
        
        super.viewDidLoad()
        print("In collection view")
        print(wineVariety)
//        print(wineType)
        wineData.onDataUpdate = {[weak self] (data: [WineList]) in self?.render()}
        wineData.loadJson(wineType, wineVariety)


        
    }
    
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }
    
    
    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of items
        return wineNames.count
    }
    
    /*https://gist.github.com/nor0x/076cef18b1e412d2f432da911b9a5bab
    rounding the cells and adding a border*/
    
    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! CollectionViewCell
        cell.wineNameLabel.text = wineNames[indexPath.row]
        // Configure the cell
        
        cell.layer.cornerRadius = 3.5
        cell.layer.borderWidth = 4.5
        cell.layer.borderColor = UIColor.black.cgColor
        
        return cell
    }
    
    func collectionView( _ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int)-> UIEdgeInsets {
        let sectionInsets = UIEdgeInsets(top: 2, left: 2.5, bottom: 0, right: 2.5)
        return sectionInsets
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showDetail" {
            if let indexPath = collectionView.indexPath(for: sender as! CollectionViewCell) {
                let wine = wines[indexPath.row]
                var regionAvailable = false
                var varietalAvailable = false
                let tempname = wine.name
                let nameArr = tempname.split(separator: " ")
                if nameArr.count > 4{
                    shortName = String(nameArr[0]) + " " + String(nameArr[1]) + " " + String(nameArr[2]) + " " + String(nameArr[3])
                }
                else
                {
                    shortName = tempname
                }
                let name = shortName
                
                if let regionTry = wine.region{
                    region = regionTry
                    regionAvailable = true
                }
                else{
                    region = "Region not available."
                    print("missing region")
                }
                if let varietalTry = wine.varietal{
                    varietal = varietalTry
                    varietalAvailable = true
                }
                else{
                    varietal = "Varietal not available"
                }
                if let vintageTry = wine.vintage{
                    vintage = vintageTry
                }
                else{
                    vintage = "Vintage not available"
                }
                if let priceTry = wine.price{
                    price = priceTry
                }
                else{
                    price = "Price not available"
                }
                if let wineryTry = wine.winery{
                    winery = wineryTry
                }
                else{
                    winery = "Winery not available"
                }
                
                if regionAvailable{
                    let regionArr = region.split(separator: ">")
                    //print(regionArr)
                    var i = regionArr.count-2
                    region = String(regionArr[i+1])
                    while i >= 0 {
                            let place = regionArr[i]
                            var placearr = place.split(separator: " ")
                            region += ", " + placearr[0]

                        
                        i -= 1
                        
                    }
                }
                print(region)
                
                if varietalAvailable{
                    let varietalArr = varietal.split(separator: ";")
                    var i = 1
                    varietal = String(varietalArr[0])
                    while i < varietalArr.count{
                        varietal += "," + varietalArr[i]
                        
                        i+=1
                        
                    }
                }
                
                
                let controller = segue.destination as! DetailViewController
                controller.title = name
//                controller.labelName = name
                controller.labelRegion = region
                controller.labelPrice = price
                controller.labelWinery = winery
                controller.labelVintage = vintage
                controller.labelVarietal = varietal
                controller.navigationItem.leftItemsSupplementBackButton = true
            }
        }
    }
    
    
}
