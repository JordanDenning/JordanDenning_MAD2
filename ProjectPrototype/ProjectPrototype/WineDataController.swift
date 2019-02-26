//
//  WineDataController.swift
//  ProjectPrototype
//
//  Created by Jordan Denning on 2/25/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import Foundation

class WineDataController{
    var wineData = WineData()
    
    var onDataUpdate: ((_ data: [WineList]) -> Void)?
    
    func loadJson(){
        let urlPath =  "https://api.snooth.com/wines/?akey=65x5832y013kw1hmvytti3p4xkfott3skvw6e5agp110n5lw&color=red"
        guard let url = URL(string: urlPath)
            else{
                print("url error")
                return
        }
        let session = URLSession.shared.dataTask(with: url, completionHandler: {(data, response, error) in
            let httpResponse = response as! HTTPURLResponse
            let statusCode = httpResponse.statusCode
            guard statusCode == 200
                else{
                    print("file download error")
                    return
            }
            print("download complete!")
            DispatchQueue.main.async {self.parseJson(data!)}
        })
        session.resume()
    }
    
    func parseJson(_ data: Data){
        do
        {
            let api = try JSONDecoder().decode((WineData.self), from: data)
            for wine in api.wines
            {
                wineData.wines.append(wine)
            }
        }
        catch let jsonErr
        {
            print("json error")
            print(jsonErr.localizedDescription)
            return
        }
        print("parsejson done")
        onDataUpdate?(wineData.wines)
    }
    
    func getWines()->[WineList]{
        return wineData.wines
    }
    
//    func getWineNames() -> [String] {
//        return wineData.wines.name
//    }

}
