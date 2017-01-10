# Timeseries API version 2.0


<a name="overview"></a>
## Overview
Write, Store, Analyze and Read Timeseries Data


### Version information
*Version* : v2


### Contact information
*Contact* : DataPlatform Team:  
                                            Vijaya Sekhar Chennupati,  
                                            Vaidhyanathan Venkiteswaran,  
                                            Sajjad Pourmohammad,  
                                            Rahul Chalindrawar,  
                                            Erik Paulson,  
                                            Dr. Youngchoon Park  
*Contact Email* : vaidhyanathan.venkiteswaran@jci.com


### License information
*Terms of service* : (C) 2008, 2010-2017 Johnson Controls International Plc.   
                                          All rights reserved. This software constitutes the trade secrets and confidential   
                                          and proprietary information of Johnson Controls International Plc. It is intended   
                                          solely for use by Johnson Controls International Plc. This code may not be copied   
                                          or redistributed to third parties without prior written authorization from   
                                          Johnson Controls International Plc.


### URI scheme
*BasePath* : /




<a name="paths"></a>
## Paths

<a name="timeseriesapiv1-0messagespost"></a>
### POST /timeseriesapi/v1.0/messages

#### Parameters

|Type|Name|Schema|
|---|---|---|
|**Body**|**messages**  <br>*optional*|< [Message](#message) > array|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* VersionOne


<a name="timeseriesapiv1-0pointupdatebypointidpost"></a>
### Update the definition of a point.
```
POST /timeseriesapi/v1.0/pointupdate/{pointId}
```


#### Description
Entity API keeps the updated point information. Every time theere is an update this endpoint should be called with the appropriate point ID to let the timeseries API that the point ID needs to be updated. By calling this endpoint the updated point info will be fetched from Entity API.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**pointId**  <br>*required*|Point ID that needs to be updated|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|Returns 204 samples inserted into persistent store|No Content|
|**401**|Unauthorized|No Content|


#### Tags

* VersionOne


<a name="timeseriesapiv1-0samplesbytimeseriesidget"></a>
### get samples for a timeseriesId
```
GET /timeseriesapi/v1.0/samples/{timeSeriesId}
```


#### Description
concept of timeseries is different in version 1 and version 2. points in timeseries version 1 are equivalent of timeseries in version 2 and timeseries in version 1 is like metrics in timeseries version 2. We will be talking in the language of timeseries api version 1 here.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**endTime**  <br>*optional*|end time (localtime + offset), default is UTC now|string(date-time)|
|**Query**|**ids**  <br>*optional*|List of additional timeseries to get|< string > array(multi)|
|**Query**|**limit**  <br>*optional*||integer(int32)|
|**Query**|**orderby**  <br>*optional*||string|
|**Query**|**startTime**  <br>*optional*|start time (localtime + offset), default is UTC now minus 1 day|string(date-time)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**400**|if not able to insert successfully into persistent store|No Content|


#### Tags

* VersionOne


<a name="timeseriesapiv1-0timeseriespost"></a>
### Post Samples to a one or more point IDs
```
POST /timeseriesapi/v1.0/timeseries
```


#### Description
This endpoint is used to post samples to a point ID.  
 
 Example usages of the POST with this endpoint:
 
    [{"id" : "new-timeseries-wrapper-01",
         "samples":[
       {"pointId":"new-timeseries-wrapper-01",
       "id":"new-timeseries-wrapper-01.Raw",
       "ts":"2016-11-03T22:38:46+00:00",
       "val":72.0},
       {"pointId":"new-timeseries-wrapper-01",
       "id":"new-timeseries-wrapper-01.Raw",
       "ts":"2016-11-07T08:53:25+00:00",
       "val":45.0},
       {"pointId":"new-timeseries-wrapper-01",
       "id":"new-timeseries-wrapper-01.Raw",
       "ts":"2016-11-04T00:56:44+00:00",
       "val":1009.0}]
       }]
    
 All parameters provided:


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**samplesIn**  <br>*optional*|Samples to be posted|< [SamplesCollection](#samplescollection) > array|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|Returns 204 samples inserted into persistent store|No Content|
|**401**|Unauthorized|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* VersionOne


<a name="timeseriesapiv1-0timeseriesget"></a>
### get samples for multiple points
```
GET /timeseriesapi/v1.0/timeseries
```


#### Description
concept of timeseries is different in version 1 and version 2. points in timeseries version 1 are equivalent of timeseries in version 2 and timeseries in version 1 is like metrics in timeseries version 2. We will be talking in the language of timeseries api version 1 here.
 
A point generally has a raw timeseries which has the original samples. The other timeseries on the point are rollups calculated based on the raw timeseries. The sum, average and delta parameters are used to select the timeseries. If you don't specify sum, average or delta then the raw timeseries will be returned. the following table shows the timeseries which is returned based on the query parameters.

    Query parameter        |         transformation type retunred
    sum=quarterly          |        TotalQuarterHour
    sum=hourly             |     TotalHourly
    sum=daily             |     TotalDaily
    sum=monthly             |     TotalMonthly
    sum=yearly             |     TotalYearly
    sum=quarterly             |     AverageQuarterHour
    sum=hourly             |     AverageHourly
    sum=daily             |     AverageDaily
    sum=monthly             |     AverageMonthly
    sum=yearly             |     AverageYearly
    sum=quarterly             |     DeltaQuarterHour
    sum=hourly             |     DeltaHourly
    sum=daily             |     DeltaDaily
    sum=monthly             |     DeltaMonthly
    sum=yearlyly             |     DeltaYearly


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**average**  <br>*optional*|specifies the period to compute average by|string|
|**Query**|**delta**  <br>*optional*|specifies the period to compute deltas by|string|
|**Query**|**endTime**  <br>*optional*|end time for the period to return samples from|string(date-time)|
|**Query**|**limit**  <br>*optional*||integer(int32)|
|**Query**|**orderby**  <br>*optional*||string|
|**Query**|**points**  <br>*optional*|the list of point IDs to get the samples from|string|
|**Query**|**startTime**  <br>*optional*|start time for the period to return samples from|string(date-time)|
|**Query**|**sum**  <br>*optional*|specifies the period to compute sum by|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**400**|if not able to insert successfully into persistent store|No Content|


#### Tags

* VersionOne


<a name="timeseriesapiv1-0timeseriesbypointidget"></a>
### get samples for a single point
```
GET /timeseriesapi/v1.0/timeseries/{pointid}
```


#### Description
concept of timeseries is different in version 1 and version 2. points in timeseries version 1 are equivalent of timeseries in version 2 and timeseries in version 1 is like metrics in timeseries version 2. We will be talking in the language of timeseries api version 1 here.
 
A point generally has a raw timeseries which has the original samples. The other timeseries on the point are rollups calculated based on the raw timeseries. The sum, average and delta parameters are used to select the timeseries. If you don't specify sum, average or delta then the raw timeseries will be returned. the following table shows the timeseries which is returned based on the query parameters.

    Query parameter        |         transformation type retunred
    sum=quarterly          |        TotalQuarterHour
    sum=hourly             |     TotalHourly
    sum=daily             |     TotalDaily
    sum=monthly             |     TotalMonthly
    sum=yearly             |     TotalYearly
    sum=quarterly             |     AverageQuarterHour
    sum=hourly             |     AverageHourly
    sum=daily             |     AverageDaily
    sum=monthly             |     AverageMonthly
    sum=yearly             |     AverageYearly
    sum=quarterly             |     DeltaQuarterHour
    sum=hourly             |     DeltaHourly
    sum=daily             |     DeltaDaily
    sum=monthly             |     DeltaMonthly
    sum=yearlyly             |     DeltaYearly


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**pointId**  <br>*required*|the point ID to get the samples from|string|
|**Query**|**average**  <br>*optional*|specifies the period to compute average by|string|
|**Query**|**delta**  <br>*optional*|specifies the period to compute deltas by|string|
|**Query**|**endTime**  <br>*optional*|end time for the period to return samples from|string(date-time)|
|**Query**|**limit**  <br>*optional*||integer(int32)|
|**Query**|**orderby**  <br>*optional*||string|
|**Query**|**startTime**  <br>*optional*|start time for the period to return samples from|string(date-time)|
|**Query**|**sum**  <br>*optional*|specifies the period to compute sum by|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|No content|No Content|
|**400**|if not able to insert successfully into persistent store|No Content|


#### Tags

* VersionOne


<a name="timeseriesapiv2-0glas-samplespost"></a>
### Post GLAS  Samples directly to the storage
```
POST /timeseriesapi/v2.0/glas-samples
```


#### Description
This endpoint is used to post GLAS samples to the storage. The samples will not go through the processing pipline. This is for internal use and will be removed in future.  
 
 Example usages of the POST with this endpoint:
 
 POST the following payload to (<a href="http://localhost:5000/timeseriesapi/v2.0/timeseries/5a5d6b30-8fc5-42f7-8c14-0cc2a6859fed/samples">Encoded localhost url</a>):   
 
       [
         {
           "timeseriesId": "GLAS.101",
           "timestamp": "2016-12-20T16:32:48+00:00",
           "value": 16
         }
       ]


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**elements**  <br>*optional*|List of samples to add to the timeseries|< [TSElement](#tselement) > array|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|Samples are successfully posted.|No Content|
|**400**|if not able to insert successfully into persistent store|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Samples


<a name="timeseriesapiv2-0operationspost"></a>
### Perform an operation on any input data
```
POST /timeseriesapi/v2.0/operations
```


#### Description
This endpoint is used to perform the supported operation on a list of sample inputs. It should be noted that there is another endpoint for metric preview which can be used to preview metrics. But this endpoint is performed on the data supplied with the POST request instead of getting the data from the datastore. 
 
Example usages to calculate QuarterHourly Average:
 
     { 
         "operation": "Aggregate",
         "output": [{"timeseriesId" : "176076", "metric":"QuarterHourlyAverage"}],
         "timeseriesId": "176076",
         "parameters": { "function": "Average", "interval": "QuarterHourly" },
         "dataPrimary": {
             "id": "176076:Raw",
             "timeseriesId": "176076",
             "metric": "Raw",
             "samples":
                 [
     	            {"id": "Raw:1464782340000", "timeseriesId": "176076", "unixTimestamp":1464782340000, "timestamp": "2016-06-01T06:59:00-05:00", "val": 60.0, "metric": "Raw" },
	              {"id": "Raw:1464782400000", "timeseriesId": "176076", "unixTimestamp":1464782400000, "timestamp": "2016-06-01T07:00:00-05:00", "val": 69.0, "metric": "Raw" },
	              {"id": "Raw:1464783060000", "timeseriesId": "176076", "unixTimestamp":1464783060000, "timestamp": "2016-06-01T07:11:00-05:00", "val": 69.4, "metric": "Raw" },
	              {"id": "Raw:1464783300000", "timeseriesId": "176076", "unixTimestamp":1464783300000, "timestamp": "2016-06-01T07:15:00-05:00", "val": 70.0, "metric": "Raw" },
	              {"id": "Raw:1464783420000", "timeseriesId": "176076", "unixTimestamp":1464783420000, "timestamp": "2016-06-01T07:17:00-05:00", "val": 80.0, "metric": "Raw" },
	              {"id": "Raw:1464783600000", "timeseriesId": "176076", "unixTimestamp":1464783600000, "timestamp": "2016-06-01T07:20:00-05:00", "val": 90.0, "metric": "Raw" }
	  
              ]         
          }
     }
    
If runs successfully, this will return the results of that operation:

     [
        {
          "metric": "QuarterHourlyAverage",
          "id": "176076:QuarterHourlyAverage",
          "samples": [
            {
              "unixTimestamp": 1464781500000,
              "val": 60,
              "timestamp": "2016-06-01T11:45:00",
              "metric": "QuarterHourlyAverage",
              "ingestUnixTimestamp": 1483550173316,
              "id": "QuarterHourlyAverage:1464781500000",
              "timeseriesId": "176076"
            },
            {
              "unixTimestamp": 1464782400000,
              "val": 69.2,
              "timestamp": "2016-06-01T12:00:00",
              "metric": "QuarterHourlyAverage",
              "ingestUnixTimestamp": 1483550173316,
              "id": "QuarterHourlyAverage:1464782400000",
              "timeseriesId": "176076"
            },
            {
              "unixTimestamp": 1464783300000,
              "val": 80,
              "timestamp": "2016-06-01T12:15:00",
              "metric": "QuarterHourlyAverage",
              "ingestUnixTimestamp": 1483550173316,
              "id": "QuarterHourlyAverage:1464783300000",
              "timeseriesId": "176076"
            }
          ],
          "timeseriesId": "176076"
        }
     ]


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**opDef**  <br>*optional*|The operation definition object. The format is as in the example shown above.|[OperationDefinition](#operationdefinition)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Returns the results of the operation|No Content|
|**401**|Unauthorized access|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Operations


<a name="timeseriesapiv2-0orgpost"></a>
### Post a new Org object for that specific OrgId
```
POST /timeseriesapi/v2.0/org
```


#### Description
If successful, it returns the created org object. As an example org2 is created and returned:
 
    {
         "id": "org2",
         "orgId": "org2",
         "timeseriesIdList": [
           "org2.ts11",
           "org2.ts22"
           ]
     }


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**org**  <br>*optional*|The Org object to be posted. The model of the object is like the example shown above.|[Org](#org)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successfully created the object. Returns the object of orgId|No Content|
|**401**|Unauthorized access|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Org


<a name="timeseriesapiv2-0orgget"></a>
### Get the list of OrgIds Samples
```
GET /timeseriesapi/v2.0/org
```


#### Description
OrgId is defined for every timeseries as the representative of the organization to which that timeseries belong. This method will return all the OrgIds across all the timeseries.        
 
 Example retunred list with this endpoint are followed:
 
    [
         "org1",
         "org2",
         "org3",
         "org4",
         "org5",
         "org6",
         "jci.com",
         "jcinew.com"
    ]


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Returns the list of orgIds|No Content|
|**401**|Unauthorized access|No Content|


#### Tags

* Org


<a name="timeseriesapiv2-0orgbyorgidget"></a>
### Get the Org object for that specific OrgId
```
GET /timeseriesapi/v2.0/org/{orgId}
```


#### Description
Returns the org object for that specific OrgId. An example retunred object is hsown below:
 
    {
         "id": "org2",
         "orgId": "org2",
         "timeseriesIdList": [
           "org2.ts11",
           "org2.ts22"
           ]
     }


#### Parameters

|Type|Name|Schema|
|---|---|---|
|**Path**|**orgId**  <br>*required*|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Returns the object of that orgId|No Content|
|**401**|Unauthorized access|No Content|


#### Tags

* Org


<a name="timeseriesapiv2-0orgbyorgidtimeseriesidspost"></a>
### Add list of timeseries to an OrgId
```
POST /timeseriesapi/v2.0/org/{orgId}/timeseriesids
```


#### Description
If successful, it returns the list of timeseries which were just added:
 
         [
           "org2.ts11",
           "org2.ts22"
         ]


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**orgId**  <br>*required*|The OrgId that the list of timeseries will be appended to|string|
|**Body**|**listOfTimeseriesIds**  <br>*optional*|The list of timeseries Ids to be added to the OrgIDd|< string > array|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successfully created added the list to the object. Returns the object of if the OrgId existed and no content if the orgId didn't exist|No Content|
|**401**|Unauthorized access|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Org


<a name="timeseriesapiv2-0orgbyorgidtimeseriesidsget"></a>
### Get the list of timeseries Ids for that specific OrgId
```
GET /timeseriesapi/v2.0/org/{orgId}/timeseriesids
```


#### Description
Returns the list of timeseriesIds for that specific OrgId. This is also accessible via using the endpoint to return the orgId object since the list of timeseries Ids is a field of that object. An example retunred object is hsown below:
 
     [
           "org2.ts11",
           "org2.ts22"
     ]


#### Parameters

|Type|Name|Schema|
|---|---|---|
|**Path**|**orgId**  <br>*required*|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Returns the object of that orgId|No Content|
|**401**|Unauthorized access|No Content|


#### Tags

* Org


<a name="timeseriesapiv2-0psrstatusbypsridget"></a>
### Get the list of PSR statuses for a psrId
```
GET /timeseriesapi/v2.0/psrstatus/{psrid}
```


#### Description
Returns all the PSR status for that perticular psrId. Every psr request sent to the platform has a unique psrId. That psr goes through all different processes in the pipeline and each of those processes generate a psr status. This endpoint returns all the psr statuses generated by the processes. There are 6 different proceses in our pipeline:

     - AcceptPSR
     - DedupRawSamples
     - GenerateETDAG
     - IngestRawSamples
     - RunDAG
     - IngestMetrics

GET /psrstatus/1477673634599

This will return a list of all the PSRs related to 1477673634599 psrId generated by the processes mentioned above.  

            [
      {
        "id": "1477673634599:AcceptPSR",
        "psrId": "1477673634599",
        "timeseriesId": "AcceptPSR",
        "unixTimestamp": 1477673634599,
        "processName": "AcceptPSR",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673634599,
        "processEndTimestamp": 1477673634599,
        "processTimeTaken": 0,
        "incomingSamplesCount": 10,
        "outgoingSamplesCount": 10,
        "offset": 1477673634599,
        "sequenceNumber": 1477673634599
      },
      {
        "id": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf:DedupRawSamples",
        "psrId": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf",
        "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
        "unixTimestamp": 1477673634599,
        "processName": "DedupRawSamples",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673634708,
        "processEndTimestamp": 1477673634708,
        "processTimeTaken": 0,
        "incomingSamplesCount": 10,
        "outgoingSamplesCount": 10,
        "offset": 5192,
        "sequenceNumber": 5
      },
      {
        "id": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf:GenerateETDAG",
        "psrId": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf",
        "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
        "unixTimestamp": 1477673634599,
        "processName": "GenerateETDAG",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673634786,
        "processEndTimestamp": 1477673634927,
        "processTimeTaken": 141,
        "incomingSamplesCount": 10,
        "outgoingSamplesCount": 10,
        "offset": 2352,
        "sequenceNumber": 1
      },
      {
        "id": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf:IngestRawSamples",
        "psrId": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf",
        "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
        "unixTimestamp": 1477673634599,
        "processName": "IngestRawSamples",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673635290,
        "processEndTimestamp": 1477673635931,
        "processTimeTaken": 641,
        "incomingSamplesCount": 10,
        "outgoingSamplesCount": 10,
        "offset": 2352,
        "sequenceNumber": 1
      },
      {
        "id": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf:RunDAG",
        "psrId": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf",
        "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
        "unixTimestamp": 1477673634599,
        "processName": "RunDAG",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673635178,
        "processEndTimestamp": 1477673635397,
        "processTimeTaken": 219,
        "incomingSamplesCount": 51,
        "outgoingSamplesCount": 51,
        "offset": 9192,
        "sequenceNumber": 1
      },
      {
        "id": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf:IngestMetrics",
        "psrId": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf",
        "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
        "unixTimestamp": 1477673634599,
        "processName": "IngestMetrics",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673635742,
        "processEndTimestamp": 1477673637813,
        "processTimeTaken": 2071,
        "incomingSamplesCount": 51,
        "outgoingSamplesCount": 51,
        "offset": 11824,
        "sequenceNumber": 2
      }
    ]


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**psrid**  <br>*required*|the PSR ID to return|integer(int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|


#### Tags

* PsrStatus


<a name="timeseriesapiv2-0samplespost"></a>
### Post Samples to multiple timeseries IDs
```
POST /timeseriesapi/v2.0/samples
```


#### Description
This endpoint is used to post samples multiple timeseries IDs at once. A list of samples are provided in the body of the POST request. Notice that posting more than 500 samples will result in multiple psrIds in the returned response. There is a mechanism that breaks up the post requests to chucnks of 500 samples at a time. 
 
 Example usages of the POST with this endpoint with two timeseries IDs in the payload:
 
 POST the following payload to (<a href="http://localhost:5000/timeseriesapi/v2.0/samples">Encoded localhost url</a>):   
 
    [
    { 
    "id": "Raw:1464325865000", 
    "timeseriesId": "5a5d6b30-8fc5-42f7-8c14-0cc2a6859fed", 
    "unixTimestamp": 1464325865000, 
    "ingestUnixTimestamp": 0, 
    "timestamp": "2016-05-27T05:11:05+00:00", 
    "value": 16, 
    "metric": "Raw" 
    }, 
    { 
    "id": "Raw:1463974604000", 
    "timeseriesId": "5a5d6b30-8fc5-42f7-8c14-0cc2a6859fed", 
    "unixTimestamp": 1463974604000, 
    "ingestUnixTimestamp": 0, 
    "timestamp": "2016-05-23T03:36:44+00:00", 
    "value": 59, 
    "metric": "Raw" 
    }, 
    { 
    "id": "Raw:1462463269000", 
    "timeseriesId": "5a5d6b30-8fc5-42f7-8c14-0cc2a6859fed", 
    "unixTimestamp": 1462463269000, 
    "ingestUnixTimestamp": 0, 
    "timestamp": "2016-05-05T15:47:49+00:00", 
    "value": 13,  
    "metric": "Raw" 
    },
    {
    "id": "Raw:1463393454000",
    "timeseriesId": "1fcfe5a3-17e1-400a-bfcd-c689513c1d63",
    "unixTimestamp": 1463393454000,
    "ingestUnixTimestamp": 0,
    "timestamp": "2016-05-16T10:10:54+00:00",
    "value": 24,
    "metric": "Raw"
    },
    {
    "id": "Raw:1463278452000",
    "timeseriesId": "1fcfe5a3-17e1-400a-bfcd-c689513c1d63",
    "unixTimestamp": 1463278452000,
    "ingestUnixTimestamp": 0,
    "timestamp": "2016-05-15T02:14:12+00:00",
    "value": 8,
    "metric": "Raw"
    }
    ]
    
 The result is the psrIds that are returned:
 
    {
       "psrIds": [
        "1477413463685"
        ]
    }
    
 All parameters provided:


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**elements**  <br>*optional*|List of samples to add to the timeseries|< [TSElement](#tselement) > array|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|Returns 204 samples inserted into persistent store|No Content|
|**400**|if not able to insert successfully into persistent store|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Samples


<a name="timeseriesapiv2-0samplesget"></a>
### Get Samples from multiple timeseries IDs
```
GET /timeseriesapi/v2.0/samples
```


#### Description
This endpoint is used to get samples from multiple timeseries IDs with same metric and time period information. If the startTime and endTime parameters are not provided it will return all the samples. To avoid an overflow number of samples to be retunred can be passed to <i>limit</i> parameter.
 The only required parameter for this enpoint is the <i>timeseries_ids</i>. 
 
 Example usages of the Get with this endpoint are followed:
 
 All parameters provided (<a href="http://localhost:5000/timeseriesapi/v2.0/samples?timeseries_ids=77&amp;timeseries_ids=174919&amp;startTime=2016-05-07T00%3A05%3A50%2B00%3A00&amp;endTime=2016-05-18T23%3A05%3A50%2B00%3A00&amp;orderby=timestamp%20asc&amp;metric=Raw&amp;limit=5">Encoded localhost url</a>):   
 
    {
       "timeseriesId": 77, 174919
       "startTime": 2016-05-07T00:05:50+00:00,
       "endTime": 2016-05-18T23:05:50+00:00,
       "orderby": timestamp asc,
       "metric": Raw,
       "limit": 5,
    }
    
 Only list of timeseriesIds provided (<a href="http://localhost:5000/timeseriesapi/v2.0/samples?timeseries_ids=77&amp;timeseries_ids=174919">Encoded localhost url</a>):
 
    {
       "timeseriesId": 77, 174919
    }
    
 All parameters provided:


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**endTime**  <br>*optional*|Ending time of the target period|string(date-time)|
|**Query**|**limit**  <br>*optional*|limit the number of samples retunred to this number|integer(int32)|
|**Query**|**metric**  <br>*optional*|metric to query the samples from|string|
|**Query**|**orderby**  <br>*optional*|order the returned samples based on the element provided here. the format is to provide one of the fields in the sample JSON space-seperated by asc/desc to choose ascending/descending order. e.g. "timestamp desc"|string|
|**Query**|**startTime**  <br>*optional*|Starting time of the target period|string(date-time)|
|**Query**|**timeseries_ids**  <br>*optional*|List of Timeseries IDs to get samples from|< string > array(multi)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Returns the samples|No Content|


#### Tags

* Samples


<a name="timeseriesapiv2-0timeseriespost"></a>
### Register new timeseries definition without metrics
```
POST /timeseriesapi/v2.0/timeseries
```


#### Description
This endpoint is used to register a single timeseries definition without any metrics. 
 
    {
         "id": "pointA",
         "timeseriesId": "pointA",
         "orgId": "jci.com",
         "timeseriesType": "baseline"
    }

Should this run successfully it will return 204 No Content


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**ts**  <br>*optional*|Timeseries object to be registered.|[Timeseries](#timeseries)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|Returns 204 samples inserted into persistent store|No Content|
|**401**|Unauthorized access|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Timeseries


<a name="timeseriesapiv2-0timeseriesbulkregisterpost"></a>
### Bulk register timeseries IDs from a template
```
POST /timeseriesapi/v2.0/timeseries/bulkregister
```


#### Description
This endpoint is used to bulk register timeseries definition from a template definition. Only 500 Ids can be registered at a time. 
 
For example, the Ids from 1 to 5 will be registered from "CsdTemplate":

       {
           "templateTimeseriesId" : "CsdTemplate",
           "timeseriesIdsToRegister" : [
               "1", "2", "3", "4", "5"
            ]
        }
    

Should this run successfully it will return 204 with no content


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**brt**  <br>*optional*|Bulk register object|[BulkRegisterTimeseries](#bulkregistertimeseries)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|No content means it did register the timeseries successfully|No Content|
|**401**|Unauthorized access|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Timeseries


<a name="timeseriesapiv2-0timeseriesnewpost"></a>
### Register new timeseries definition
```
POST /timeseriesapi/v2.0/timeseries/new
```


#### Description
This endpoint is used to register a single or multiple timeseries definition. timeseriesId will be randomly generated. timeseriesType will be always baseline. There is no need to provide any data in the POST request body. Depending on the number of timeseries to register it will generate some randome baseline timeseries.:
 
    {
        // no need for a body
    }

Should this run successfully it will return the created timeseries

for example for n=2 it returns:

    [{
       "id": "a4b2bd11-a153-4890-9a2e-8c3958de65d6",
       "timeseriesId": "a4b2bd11-a153-4890-9a2e-8c3958de65d6",
       "orgId" : "jci.com",
       "timeseriesType": "baseline"
     },
     {
       "id": "3514a88e-1084-4129-a434-c4063dd07ef2",
       "timeseriesId": "3514a88e-1084-4129-a434-c4063dd07ef2",
       "timeseriesType": "baseline"
     }
     ]


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**n**  <br>*optional*|Number of timeseries IDs to register|integer(int32)|
|**Query**|**orgId**  <br>*optional*||string|
|**Query**|**timeseriesType**  <br>*optional*||string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|Returns 204 samples inserted into persistent store|No Content|
|**401**|Unauthorized|No Content|


#### Tags

* Timeseries


<a name="timeseriesapiv2-0timeseriesbytimeseriesidget"></a>
### get the definition of a timeseries
```
GET /timeseriesapi/v2.0/timeseries/{timeseriesId}
```


#### Description
This endpoint is used to request the definition of a timeseries.
 
GET timeseries/pointA

Should this run successfully it will return the timeseries definition

           {
     "id": "pointA",
     "timeseriesId": "pointA",
     "orgId": "jci.com",
     "dag": [
       {
         "metric": "DeltaDaily",
         "operations": [
           {
             "operation": "Aggregate",
             "input": [
               {
                 "timeseriesId": "pointA",
                 "metric": "Raw"
               }
             ],
             "output": [
               {
                 "timeseriesId": "pointA",
                 "metric": "DeltaDaily"
               }
             ],
             "parameters": {
               "function": "Delta",
               "interval": "Daily"
             }
           }
         ],
         "processingType": "stream"
       }                
     ]
   }


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**timeseriesId**  <br>*required*|timeseries IDs to get the definition from|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|No content|No Content|
|**401**|Unauthorized|No Content|


#### Tags

* Timeseries


<a name="timeseriesapiv2-0timeseriesbytimeseriesiddatebydtsamplescountget"></a>
### Get the number of Samples for a timeseriesId for an specific date
```
GET /timeseriesapi/v2.0/timeseries/{timeseriesId}/date/{dt}/samplescount
```


#### Description
retuns the number of samples for a timeseriesID and all the metrics for a given date.  
 
 Example retuned response:
 
       [
         {
           "count": 1532,
           "date": "2017-01-05",
           "timeseriesId": "pointA",
           "metric": "Raw"
         },
         {
           "count": 24,
           "date": "2017-01-05",
           "timeseriesId": "pointA",
           "metric": "HourlyAverage"
         }
       ]


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**dt**  <br>*required*|the date to query the samples from|string(date-time)|
|**Path**|**timeseriesId**  <br>*required*|Timeseries ID to get samples from|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Sample count for each day|No Content|
|**401**|Unauthorized|No Content|


#### Tags

* Samples


<a name="timeseriesapiv2-0timeseriesbytimeseriesidlastnsamplesget"></a>
### Get last N Samples from a single timeseries ID
```
GET /timeseriesapi/v2.0/timeseries/{timeseriesId}/lastnsamples
```


#### Description
retuns the last N samples from a timeseries corresponding to a metric. The only required parameter for this enpoint is n. 
 
 Example:
 
 All parameters provided (<a href="http://localhost:5000/timeseriesapi/v2.0/timeseries/77/lastnsamples?n=5&amp;metric=Raw">Encoded localhost url</a>):   
 
    {
       "timeseriesId": 77,
       "n": 5,
       "metric": Raw
    }
    
 Only timeseriesId is provided (<a href="http://localhost:5000/timeseriesapi/v2.0/timeseries/77/lastnsamples">Encoded localhost url</a>):   
 
    {
       "timeseriesId": 77,
    }


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**timeseriesId**  <br>*required*|Timeseries ID to get samples from|string|
|**Query**|**metric**  <br>*optional*|metric to query the samples from|string|
|**Query**|**n**  <br>*optional*|number of latest samples to return|integer(int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|Returns 204 samples inserted into persistent store|No Content|


#### Tags

* Samples


<a name="timeseriesapiv2-0timeseriesbytimeseriesidmetricbymetricnamesamplescountget"></a>
### Get the daily number of Samples for a timeseriesId and metric
```
GET /timeseriesapi/v2.0/timeseries/{timeseriesId}/metric/{metricName}/samplescount
```


#### Description
retuns the number of samples for a timeseriesID and a metric grouped by date.  
 
 Example retuned response:
 
       [
         {
           "count": 5,
           "date": "2017-01-05",
           "timeseriesId": "672aaf93-5905-48ef-a96f-70527adb7955",
           "metric": "Raw"
         },
         {
           "count": 1,
           "date": "2017-01-06",
           "timeseriesId": "672aaf93-5905-48ef-a96f-70527adb7955",
           "metric": "Raw"
         }
       ]


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**metricName**  <br>*required*|metric to query the samples from|string|
|**Path**|**timeseriesId**  <br>*required*|Timeseries ID to get samples from|string|
|**Query**|**endDate**  <br>*optional*||string(date-time)|
|**Query**|**startDate**  <br>*optional*||string(date-time)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Sample count for each day|No Content|
|**401**|Unauthorized|No Content|


#### Tags

* Samples


<a name="timeseriesapiv2-0timeseriesbytimeseriesidmetricspost"></a>
### Add or update metrics to timeseries definition
```
POST /timeseriesapi/v2.0/timeseries/{timeseriesId}/metrics
```


#### Description
A metric is a series of operations that are applied to the timeseries data to produce meaningful insights about the data. Timeseries API supports bunch of aggregation, filtering and arithmetic operations that can run on the timeseries data. Every operation should have input(s) and output(s) and set of parameters required by that operation. Refer to the documentation of supported operations on timeseries API for detailed description of the operations. The model for posting metrics is shown below:
 
       [{
           "metric": string  // name of the metric,
           "processingType":  // the type of the processing "batch" or stream,
           "frequency":  // the frequency of the operation period in case of a batch processing,
           "operations": [  // list of operations for that metric
               {
                   "operation": string // name of the operation supported by the API,
                   "input": [string], // list of input(s) to the operation
                   "output": [string] // list of output(s) to be generated,
                   "parameters": {} // a dictionary containing the parameters of that operation
                   }
               }
           ]
       }
        ]

POST timeseries/06bc8071-357a-48ed-bc81-a5c67eb1e27b/metrics

         [{
           "metric": "BoundsLimitingCleanse",
           "operations": [
               {
                   "operation": "BoundsLimitingCleanse",
                   "input": [
                       "06bc8071-357a-48ed-bc81-a5c67eb1e27b:MuteErrorsCleanse"
                   ],
                   "output": [
                       "06bc8071-357a-48ed-bc81-a5c67eb1e27b:BoundsLimitingCleanse",
                       "06bc8071-357a-48ed-bc81-a5c67eb1e27b:IsCleansed"
                   ],
                   "parameters": {
                       "UpperBound": 46664,
                       "LowerBound": -44,
                       "NormalizationType": 1
                   }
               }
           ]
       }
        ]
        
This will add BoundsLimitingCleanse to timeseries ID 06bc8071-357a-48ed-bc81-a5c67eb1e27b. 

This will also update the list of batch period lists in case a batch metric is added to the timeseries. 

for a list of all the operations supported refer to the documentation.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**timeseriesId**  <br>*required*|timeseries ID to add metrics to|string|
|**Query**|**withValidation**  <br>*optional*||boolean|
|**Body**|**metrics**  <br>*optional*|list of metrics to add|< [Metric](#metric) > array|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|No content|No Content|
|**401**|Unauthorized|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Timeseries


<a name="timeseriesapiv2-0timeseriesbytimeseriesidmetricsget"></a>
### get the metrics from a timeseries definition.
```
GET /timeseriesapi/v2.0/timeseries/{timeseriesId}/metrics
```


#### Description
This endpoint is used to request the metrics in a timeseries. A metric is a series of operations that are applied to the timeseries data to produce meaningful insights about the data. Timeseries API supports bunch of aggregation, filtering and arithmetic operations that can run on the timeseries data. Every operation should have input(s) and output(s) and set of parameters required by that operation. This endpoint will return all those information about the metrics. 
 
GET timeseries/pointA/metrics

Should this run successfully it will return the timeseries definition

               [
         {
           "metric": "DeltaDaily",
           "operations": [
             {
               "operation": "Aggregate",
               "input": [
                 {
                   "timeseriesId": "pointA",
                   "metric": "Raw"
                 }
               ],
               "output": [
                 {
                   "timeseriesId": "pointA",
                   "metric": "DeltaDaily"
                 }
               ],
               "parameters": {
                 "function": "Delta",
                 "interval": "Daily"
               }
             }
           ],
           "processingType": "stream"
         },
         {
           "metric": "DeltaHourly",
           "operations": [
             {
               "operation": "Aggregate",
               "input": [
                 {
                   "timeseriesId": "pointA",
                   "metric": "Raw"
                 }
               ],
               "output": [
                 {
                   "timeseriesId": "pointA",
                   "metric": "DeltaHourly"
                 }
               ],
               "parameters": {
                 "function": "Delta",
                 "interval": "Hourly"
               }
             }
           ],
           "processingType": "stream"
         },


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**timeseriesId**  <br>*required*|timeseries ID to return metrics from|string|
|**Query**|**processingType**  <br>*optional*||string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Successful|No Content|
|**401**|Unauthorized|No Content|


#### Tags

* Timeseries


<a name="timeseriesapiv2-0timeseriesbytimeseriesidmetricsdelete"></a>
### Delete metrics from a timeseries definition
```
DELETE /timeseriesapi/v2.0/timeseries/{timeseriesId}/metrics
```


#### Description
Metrics can be deleted using this endpoint. Supply the list of metric names in the DELETE request body.
 
DELETE timeseries/06bc8071-357a-48ed-bc81-a5c67eb1e27b/metrics

         ["MuteErrorsCleanse", "BoundsLimitingCleanse"]
        
        
This will delete MuteErrorsCleanse and BoundsLimitingCleanse from timeseries ID 06bc8071-357a-48ed-bc81-a5c67eb1e27b. 
This method will also remove that timeseriesId from the corresponsing batch period list if there is no other batch metric left in the timeseries definition after removing a batch metric


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**timeseriesId**  <br>*required*|timeseries ID to delete the metrics from|string|
|**Body**|**metricNames**  <br>*optional*|list of metric names to delete|< string > array|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|No content means successfully deleted the metrics|No Content|
|**401**|Unauthorized|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Timeseries


<a name="timeseriesapiv2-0timeseriesbytimeseriesidmetricspreviewpost"></a>
### Preview a metric
```
POST /timeseriesapi/v2.0/timeseries/{timeseriesId}/metrics/preview
```


#### Description
This endpoint can be used to preview the results of a metric before posting it to the timeseries. A metric is a series of operations that are applied to the timeseries data to produce meaningful insights about the data. Timeseries API supports bunch of aggregation, filtering and arithmetic operations that can run on the timeseries data. Every operation should have input(s) and output(s) and set of parameters required by that operation. Refer to the documentation of supported operations on timeseries API for detailed description of the operations. The model for posting metrics is shown below:
 
       [{
           "metric": string  // name of the metric,
           "processingType":  // the type of the processing "batch" or stream,
           "frequency":  // the frequency of the operation period in case of a batch processing,
           "operations": [  // list of operations for that metric
               {
                   "operation": string // name of the operation supported by the API,
                   "input": [string], // list of input(s) to the operation
                   "output": [string] // list of output(s) to be generated,
                   "parameters": {} // a dictionary containing the parameters of that operation
                   }
               }
           ]
       }
        ]

POST timeseries/897d045b-5c25-45e9-85cb-c706fc05400d/metrics/preview

        {
        	"metrics": [{
        		"metric": "BoundsLimitingCleanse",
        		"processingType": "batch",
        		"frequency": "PT15M",
        		"operations": [{
        			"operation": "BoundsLimitingCleanse",
        			"input": [
        				"897d045b-5c25-45e9-85cb-c706fc05400d:Raw"
        			],
        			"output": [
        				"897d045b-5c25-45e9-85cb-c706fc05400d:BoundsLimitingCleanse",
        				"897d045b-5c25-45e9-85cb-c706fc05400d:IsCleansed"
        			],
        			"parameters": [{
        				"UpperBound": 46664,
        				"LowerBound": -44,
        				"NormalizationType": 1
        
                    }]
        		}]
        	}],
        	"startTime": 1472831624956,
        	"endTime": 1479839629956
        }
        
This will return the output from BoundsLimitingCleanse applied to that timeseriesID:

        [
         {
           "id": "BoundsLimitingCleanse:1479539411000",
           "timeseriesId": "897d045b-5c25-45e9-85cb-c706fc05400d",
           "unixTimestamp": 1479539411000,
           "ingestUnixTimestamp": 0,
           "timestamp": "2016-11-19T07:10:11+00:00",
           "value": 36,
           "metric": "BoundsLimitingCleanse"
         },
         {
           "id": "BoundsLimitingCleanse:1478215153000",
           "timeseriesId": "897d045b-5c25-45e9-85cb-c706fc05400d",
           "unixTimestamp": 1478215153000,
           "ingestUnixTimestamp": 0,
           "timestamp": "2016-11-03T23:19:13+00:00",
           "value": 23,
           "metric": "BoundsLimitingCleanse"
         }]
 
for a list of all the operations supported refer to the documentation.


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**timeseriesId**  <br>*required*|timeseries ID to add metrics to|string|
|**Query**|**withValidation**  <br>*optional*||boolean|
|**Body**|**previewRequest**  <br>*optional*|preview request object containing the metrics, start time and end time|[PreviewRequest](#previewrequest)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK with the results returned|No Content|
|**401**|Unauthorized|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Timeseries


<a name="timeseriesapiv2-0timeseriesbytimeseriesidsamplespost"></a>
### Post Samples to a single timeseries ID
```
POST /timeseriesapi/v2.0/timeseries/{timeseriesId}/samples
```


#### Description
This endpoint is used to post samples to a single timeseries ID. A list of samples are provided in the body of the POST request. Notice that posting more than 500 samples will result in multiple psrIds in the returned response. There is a mechanism that breaks up the post requests to chucnks of 500 samples at a time. 
 
 Example usages of the POST with this endpoint:
 
 POST the following payload to (<a href="http://localhost:5000/timeseriesapi/v2.0/timeseries/5a5d6b30-8fc5-42f7-8c14-0cc2a6859fed/samples">Encoded localhost url</a>):   
 
    [
    { 
    "id": "Raw:1464325865000", 
    "timeseriesId": "5a5d6b30-8fc5-42f7-8c14-0cc2a6859fed", 
    "unixTimestamp": 1464325865000, 
    "ingestUnixTimestamp": 0, 
    "timestamp": "2016-05-27T05:11:05+00:00", 
    "value": 16, 
    "metric": "Raw" 
    }, 
    { 
    "id": "Raw:1463974604000", 
    "timeseriesId": "5a5d6b30-8fc5-42f7-8c14-0cc2a6859fed", 
    "unixTimestamp": 1463974604000, 
    "ingestUnixTimestamp": 0, 
    "timestamp": "2016-05-23T03:36:44+00:00", 
    "value": 59, 
    "metric": "Raw" 
    }, 
    { 
    "id": "Raw:1462463269000", 
    "timeseriesId": "5a5d6b30-8fc5-42f7-8c14-0cc2a6859fed", 
    "unixTimestamp": 1462463269000, 
    "ingestUnixTimestamp": 0, 
    "timestamp": "2016-05-05T15:47:49+00:00", 
    "value": 13,  
    "metric": "Raw" 
    }
    ]
    
 The result is the psrIds that are returned:
 
    {
       "psrIds": [
        "1477412260594"
        ]
    }
    
 All parameters provided:


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**timeseriesId**  <br>*required*|Timeseries ID that the samples will be posted to|string|
|**Body**|**elements**  <br>*optional*|List of samples to add to the timeseries|< [TSElement](#tselement) > array|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|
|**204**|Returns 204 samples inserted into persistent store|No Content|
|**400**|if not able to insert successfully into persistent store|No Content|


#### Consumes

* `application/json`
* `text/json`
* `application/json-patch+json`


#### Tags

* Samples


<a name="timeseriesapiv2-0timeseriesbytimeseriesidsamplesget"></a>
### Get Samples for a single timeseries ID
```
GET /timeseriesapi/v2.0/timeseries/{timeseriesId}/samples
```


#### Description
This endpoint is used to get samples for a single timeseries ID and metric within a specific time period. If the startTime and endTime parameters are not provided it will return all the samples. To avoid an overflow number of samples to be retunred can be passed to <i>limit</i> parameter.
 The only required parameter for this enpoint is the <i>timeseriesId</i>. 
 
 Example usages of the Get with this endpoint are followed:
 
 All parameters provided (<a href="http://localhost:5000/timeseriesapi/v2.0/timeseries/77/samples?startTime=2016-05-07T00%3A05%3A50%2B00%3A00&amp;endTime=2016-05-18T23%3A05%3A50%2B00%3A00&amp;orderby=timestamp%20desc&amp;metric=Raw&amp;limit=3">Encoded localhost url</a>):   
 
    {
       "timeseriesId": 77,
       "startTime": 2016-05-07T00:05:50+00:00,
       "endTime": 2016-05-18T23:05:50+00:00,
       "orderby": timestamp desc,
       "metric": Raw,
       "limit": 3,
    }
    
 Only timeseriesId is provided (<a href="http://localhost:5000/timeseriesapi/v2.0/timeseries/77/samples">Encoded localhost url</a>):
 
    {
       "timeseriesId": 77
    }
    
 All parameters provided:


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**timeseriesId**  <br>*required*|Timeseries ID to get samples from|string|
|**Query**|**endTime**  <br>*optional*|Ending time of the target period|string(date-time)|
|**Query**|**limit**  <br>*optional*|limit the number of samples retunred to this number|integer(int32)|
|**Query**|**metric**  <br>*optional*|metric to query the samples from|string|
|**Query**|**orderby**  <br>*optional*|order the returned samples based on the element provided here. the format is to provide one of the fields in the sample JSON space-seperated by asc/desc to choose ascending/descending order. e.g. "timestamp desc"|string|
|**Query**|**startTime**  <br>*optional*|Starting time of the target period|string(date-time)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Returns the samples|No Content|


#### Tags

* Samples


<a name="timeseriesapiv2-0timeseriesbytimeseriesidsamplesgenerateandpostpost"></a>
### generate and post samples
```
POST /timeseriesapi/v2.0/timeseries/{timeseriesId}/samples/generateandpost
```


#### Description
It will generate random samples for the specified timeseriesId and post them. The number of desired samples can be supplied as a query parameter. 
 
 Example:
 
 generate 3 samples for timeseries 77:   
 
    POST timeseries/77/samples/generateandpost?numberOfSamples=10
    
 this will return "OK" if successful:


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**timeseriesId**  <br>*required*|Timeseries ID to post samples to|string|
|**Query**|**numberOfSamples**  <br>*optional*|number of samples to post|integer(int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Returns 200 if samples are successfully posted|No Content|


#### Tags

* Samples


<a name="timeseriesapiv2-0timeseriesbytimeseriesidpsrstatusget"></a>
### Get the list of PSR statuses for a timeseries
```
GET /timeseriesapi/v2.0/timeseries/{timeseriesid}/psrstatus
```


#### Description
Returns all the PSR status for that perticular timeseries in the last 24 hours. Every psr request sent to the platform has a unique psrId. That psr goes through all different processes in the pipeline and each of those processes generate a psr status. This endpoint returns all the psr statuses generated by the processes. There are 6 different proceses in our pipeline:

     - AcceptPSR
     - DedupRawSamples
     - GenerateETDAG
     - IngestRawSamples
     - RunDAG
     - IngestMetrics

this endpoint will return all the psr statuses from different psrIds related to that timeseries in the last 24 hours. 
 
     GET timeseries/506a006a-ef77-443e-890c-39f72968a4bf/psrstatus 

This will return a list of all the PSR statuses related to timeseries 506a006a-ef77-443e-890c-39f72968a4bf.  

                [
          {
            "id": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf:DedupRawSamples",
            "psrId": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf",
            "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
            "unixTimestamp": 1477673579666,
            "processName": "DedupRawSamples",
            "processStatus": "Completed",
            "processStartTimestamp": 1477673580354,
            "processEndTimestamp": 1477673580400,
            "processTimeTaken": 46,
            "incomingSamplesCount": 10,
            "outgoingSamplesCount": 10,
            "offset": 6872,
            "sequenceNumber": 5
          },
          {
            "id": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf:GenerateETDAG",
            "psrId": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf",
            "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
            "unixTimestamp": 1477673579666,
            "processName": "GenerateETDAG",
            "processStatus": "Completed",
            "processStartTimestamp": 1477673580791,
            "processEndTimestamp": 1477673581123,
            "processTimeTaken": 332,
            "incomingSamplesCount": 10,
            "outgoingSamplesCount": 10,
            "offset": 0,
            "sequenceNumber": 0
          },
          {
            "id": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf:IngestRawSamples",
            "psrId": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf",
            "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
            "unixTimestamp": 1477673579666,
            "processName": "IngestRawSamples",
            "processStatus": "Completed",
            "processStartTimestamp": 1477673581284,
            "processEndTimestamp": 1477673582112,
            "processTimeTaken": 828,
            "incomingSamplesCount": 10,
            "outgoingSamplesCount": 10,
            "offset": 0,
            "sequenceNumber": 0
          },
          {
            "id": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf:RunDAG",
            "psrId": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf",
            "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
            "unixTimestamp": 1477673579666,
            "processName": "RunDAG",
            "processStatus": "Completed",
            "processStartTimestamp": 1477673581498,
            "processEndTimestamp": 1477673581920,
            "processTimeTaken": 422,
            "incomingSamplesCount": 38,
            "outgoingSamplesCount": 38,
            "offset": 9192,
            "sequenceNumber": 1
          },
          {
            "id": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf:IngestMetrics",
            "psrId": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf",
            "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
            "unixTimestamp": 1477673579666,
            "processName": "IngestMetrics",
            "processStatus": "Completed",
            "processStartTimestamp": 1477673582532,
            "processEndTimestamp": 1477673584627,
            "processTimeTaken": 2095,
            "incomingSamplesCount": 38,
            "outgoingSamplesCount": 38,
            "offset": 1104,
            "sequenceNumber": 2
          },
          {
            "id": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf:DedupRawSamples",
            "psrId": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf",
            "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
            "unixTimestamp": 1477673634599,
            "processName": "DedupRawSamples",
            "processStatus": "Completed",
            "processStartTimestamp": 1477673634708,
            "processEndTimestamp": 1477673634708,
            "processTimeTaken": 0,
            "incomingSamplesCount": 10,
            "outgoingSamplesCount": 10,
            "offset": 5192,
            "sequenceNumber": 5
          },
          {
            "id": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf:GenerateETDAG",
            "psrId": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf",
            "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
            "unixTimestamp": 1477673634599,
            "processName": "GenerateETDAG",
            "processStatus": "Completed",
            "processStartTimestamp": 1477673634786,
            "processEndTimestamp": 1477673634927,
            "processTimeTaken": 141,
            "incomingSamplesCount": 10,
            "outgoingSamplesCount": 10,
            "offset": 2352,
            "sequenceNumber": 1
          },
          {
            "id": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf:IngestRawSamples",
            "psrId": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf",
            "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
            "unixTimestamp": 1477673634599,
            "processName": "IngestRawSamples",
            "processStatus": "Completed",
            "processStartTimestamp": 1477673635290,
            "processEndTimestamp": 1477673635931,
            "processTimeTaken": 641,
            "incomingSamplesCount": 10,
            "outgoingSamplesCount": 10,
            "offset": 2352,
            "sequenceNumber": 1
          },
          {
            "id": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf:RunDAG",
            "psrId": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf",
            "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
            "unixTimestamp": 1477673634599,
            "processName": "RunDAG",
            "processStatus": "Completed",
            "processStartTimestamp": 1477673635178,
            "processEndTimestamp": 1477673635397,
            "processTimeTaken": 219,
            "incomingSamplesCount": 51,
            "outgoingSamplesCount": 51,
            "offset": 9192,
            "sequenceNumber": 1
          },
          {
            "id": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf:IngestMetrics",
            "psrId": "1477673634599:506a006a-ef77-443e-890c-39f72968a4bf",
            "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
            "unixTimestamp": 1477673634599,
            "processName": "IngestMetrics",
            "processStatus": "Completed",
            "processStartTimestamp": 1477673635742,
            "processEndTimestamp": 1477673637813,
            "processTimeTaken": 2071,
            "incomingSamplesCount": 51,
            "outgoingSamplesCount": 51,
            "offset": 11824,
            "sequenceNumber": 2
          }
        ]


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**timeseriesid**  <br>*required*|the timeseries ID|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|


#### Tags

* PsrStatus


<a name="timeseriesapiv2-0timeseriesbytimeseriesidpsrstatusbypsridget"></a>
### Get the list of PSR statuses for a perticular timeseries and psrId
```
GET /timeseriesapi/v2.0/timeseries/{timeseriesid}/psrstatus/{psrid}
```


#### Description
Returns all the PSR status for that perticular timeseries in the last 24 hours that is related to a perticular psrId. Every psr request sent to the platform has a unique psrId. That psr goes through all different processes in the pipeline and each of those processes generate a psr status. This endpoint returns all the psr statuses generated by the processes. There are 6 different proceses in our pipeline:

     - AcceptPSR
     - DedupRawSamples
     - GenerateETDAG
     - IngestRawSamples
     - RunDAG
     - IngestMetrics

this endpoint will return all the psr statuses for a perticular psrId related to that timeseries in the last 24 hours. 
 
     GET timeseries/506a006a-ef77-443e-890c-39f72968a4bf/psrstatus/1477673579666

This will return a list of all the PSR statuses related to 1477673579666 psrId and timeseries 506a006a-ef77-443e-890c-39f72968a4bf generated by the processes mentioned above.  

            [
      {
        "id": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf:DedupRawSamples",
        "psrId": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf",
        "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
        "unixTimestamp": 1477673579666,
        "processName": "DedupRawSamples",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673580354,
        "processEndTimestamp": 1477673580400,
        "processTimeTaken": 46,
        "incomingSamplesCount": 10,
        "outgoingSamplesCount": 10,
        "offset": 6872,
        "sequenceNumber": 5
      },
      {
        "id": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf:GenerateETDAG",
        "psrId": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf",
        "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
        "unixTimestamp": 1477673579666,
        "processName": "GenerateETDAG",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673580791,
        "processEndTimestamp": 1477673581123,
        "processTimeTaken": 332,
        "incomingSamplesCount": 10,
        "outgoingSamplesCount": 10,
        "offset": 0,
        "sequenceNumber": 0
      },
      {
        "id": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf:IngestRawSamples",
        "psrId": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf",
        "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
        "unixTimestamp": 1477673579666,
        "processName": "IngestRawSamples",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673581284,
        "processEndTimestamp": 1477673582112,
        "processTimeTaken": 828,
        "incomingSamplesCount": 10,
        "outgoingSamplesCount": 10,
        "offset": 0,
        "sequenceNumber": 0
      },
      {
        "id": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf:RunDAG",
        "psrId": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf",
        "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
        "unixTimestamp": 1477673579666,
        "processName": "RunDAG",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673581498,
        "processEndTimestamp": 1477673581920,
        "processTimeTaken": 422,
        "incomingSamplesCount": 38,
        "outgoingSamplesCount": 38,
        "offset": 9192,
        "sequenceNumber": 1
      },
      {
        "id": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf:IngestMetrics",
        "psrId": "1477673579666:506a006a-ef77-443e-890c-39f72968a4bf",
        "timeseriesId": "506a006a-ef77-443e-890c-39f72968a4bf",
        "unixTimestamp": 1477673579666,
        "processName": "IngestMetrics",
        "processStatus": "Completed",
        "processStartTimestamp": 1477673582532,
        "processEndTimestamp": 1477673584627,
        "processTimeTaken": 2095,
        "incomingSamplesCount": 38,
        "outgoingSamplesCount": 38,
        "offset": 1104,
        "sequenceNumber": 2
      }
    ]


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**psrid**  <br>*required*|the PSR ID|integer(int64)|
|**Path**|**timeseriesid**  <br>*required*|the timeseries ID|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|Success|No Content|


#### Tags

* PsrStatus




<a name="definitions"></a>
## Definitions

<a name="bulkregistertimeseries"></a>
### BulkRegisterTimeseries

|Name|Schema|
|---|---|
|**templateTimeseriesId**  <br>*optional*|string|
|**timeseriesIdsToRegister**  <br>*optional*|< string > array|


<a name="message"></a>
### Message

|Name|Schema|
|---|---|
|**pointId**  <br>*optional*|string|
|**timeseriesId**  <br>*optional*|string|
|**ts**  <br>*optional*|string(date-time)|
|**val**  <br>*optional*|object|


<a name="metric"></a>
### Metric

|Name|Schema|
|---|---|
|**frequency**  <br>*optional*|string|
|**metric**  <br>*optional*|string|
|**operations**  <br>*optional*|< [OperationDefinition](#operationdefinition) > array|
|**processingType**  <br>*optional*|string|
|**supplementalData**  <br>*optional*|< [MetricSeries](#metricseries) > array|


<a name="metrickey"></a>
### MetricKey

|Name|Schema|
|---|---|
|**metric**  <br>*optional*|string|
|**timeseriesId**  <br>*optional*|string|


<a name="metricseries"></a>
### MetricSeries

|Name|Schema|
|---|---|
|**id**  <br>*optional*|string|
|**metric**  <br>*optional*|string|
|**samples**  <br>*optional*|< [TSElement](#tselement) > array|
|**timeseriesId**  <br>*optional*|string|


<a name="operationdefinition"></a>
### OperationDefinition

|Name|Schema|
|---|---|
|**dataPrimary**  <br>*optional*|[MetricSeries](#metricseries)|
|**dataSecondary**  <br>*optional*|[MetricSeries](#metricseries)|
|**input**  <br>*optional*|< [MetricKey](#metrickey) > array|
|**operation**  <br>*optional*|string|
|**output**  <br>*optional*|< [MetricKey](#metrickey) > array|
|**parameters**  <br>*optional*|< string, object > map|
|**timeseriesId**  <br>*optional*|string|


<a name="org"></a>
### Org

|Name|Schema|
|---|---|
|**id**  <br>*optional*|string|
|**orgId**  <br>*optional*|string|
|**timeseriesIdList**  <br>*optional*|< string > array|


<a name="previewrequest"></a>
### PreviewRequest

|Name|Schema|
|---|---|
|**endTime**  <br>*optional*|integer(int64)|
|**metrics**  <br>*optional*|< [Metric](#metric) > array|
|**startTime**  <br>*optional*|integer(int64)|


<a name="sample"></a>
### Sample

|Name|Schema|
|---|---|
|**id**  <br>*optional*|string|
|**pointId**  <br>*optional*|string|
|**ts**  <br>*optional*|string(date-time)|
|**val**  <br>*optional*|object|


<a name="samplescollection"></a>
### SamplesCollection

|Name|Schema|
|---|---|
|**id**  <br>*optional*|string|
|**samples**  <br>*optional*|< [Sample](#sample) > array|


<a name="tselement"></a>
### TSElement

|Name|Schema|
|---|---|
|**id**  <br>*optional*|string|
|**ingestUnixTimestamp**  <br>*optional*|integer(int64)|
|**metric**  <br>*optional*|string|
|**timeseriesId**  <br>*optional*|string|
|**timestamp**  <br>*optional*|string(date-time)|
|**unixTimestamp**  <br>*optional*|integer(int64)|
|**val**  <br>*optional*|object|


<a name="timeseries"></a>
### Timeseries

|Name|Schema|
|---|---|
|**ctsTriggers**  <br>*optional*|< string > array|
|**dag**  <br>*optional*|< [Metric](#metric) > array|
|**id**  <br>*optional*|string|
|**orgId**  <br>*optional*|string|
|**samples**  <br>*optional*|< [TSElement](#tselement) > array|
|**timeseriesId**  <br>*optional*|string|
|**timeseriesType**  <br>*optional*|string|




<a name="securityscheme"></a>
## Security

<a name="bearer"></a>
### Bearer
*Type* : apiKey  
*Name* : Authorization  
*In* : HEADER



