//
//  WineDataController.swift
//  ProjectFinal
//
//  Created by Jordan Denning on 3/8/19.
//  Copyright © 2019 Jordan Denning. All rights reserved.
//

import Foundation

class WineDataController{
    var wineData = WineData()
    var newVarietal = String()
    
    var onDataUpdate: ((_ data: [WineList]) -> Void)?
    /*https://stackoverflow.com/questions/5806852/split-an-nsstring-to-access-one-particular-piece for parsing strings in xcode based on seperator*/

    func loadJson(_ wineType: String, _ varietal: String){
        print(varietal)
        let substring = varietal.split(separator: " ")
        if (substring.count > 1)
        {
            newVarietal = String(substring[0]) + "+" + String(substring[1])
        }
        else
        {
            newVarietal = String(substring[0])

        }
        print(newVarietal)
        let urlPath =  "https://api.snooth.com/wines/?akey=65x5832y013kw1hmvytti3p4xkfott3skvw6e5agp110n5lw&n=20&color=" + wineType + "&q=" + newVarietal
//        let urlPath =  "https://api.snooth.com/wines/?akey=65x5832y013kw1hmvytti3p4xkfott3skvw6e5agp110n5lw&n=20&color=red"
        print(urlPath)
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
    /*https://stackoverflow.com/questions/46959625/the-data-couldn-t-be-read-because-it-is-missing-error-when-decoding-json-in-sw for data is missing error. needed to make values optional and check to see if there or give other value*/
    
    func getWines()->[WineList]{
        return wineData.wines
    }

    
}
