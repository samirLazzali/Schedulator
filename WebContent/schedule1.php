<?php
require('fpdf181/fpdf.php');

class PDF extends FPDF
{
    // Chargement des donnees
    function LoadData($file)
    {
    	//$currentCalendar = $this->recupCalendar($file, 0);
    	
        // Lecture des lignes du fichier
        $lines  = file($file);
        $data   = array();
        foreach($lines as $line)
            $data[] = explode(';', trim($line));
        
        return $data;
    }
    
    function recupCalendar($file, $whichCal)
    {
    	$threeCalendars = file_get_contents($file);
    	$tabCalendars[] = explode('###', $threeCalendars);
    	$currentCalendar = $tabCalendars[$whichCal];
    	
    	return $currentCalendar;
    }
    
    function checkFreeSpace($previousBlock, $followingBlock, $wcol, $hcol)
    {
    	$diff = $this->diffSplit($previousBlock, $followingBlock);
    	    	
    	if ($diff != 0)
    	{
    		$this->createBlock($followingBlock, $previousBlock, '', $wcol, $hcol, false);
    	}
    }
    
    function diffSplit($previous, $following)
    {
    	$endOfPreviousBlock 		= $this->splitHour($previous);
    	$beginingOfFollowingBlock 	= $this->splitHour($following);
    	
    	$diff = $beginingOfFollowingBlock - $endOfPreviousBlock;
    	
    	return $diff;
    }

    function byFive($multiple)
    {
        //divison par 5
        $resultByFive = $multiple/5;
        return $resultByFive;
    }

    function splitHour($hourToSplit)
    {
        //decoupage de l'heure
        $splitedHour  = explode(':', $hourToSplit);

        //on recupere l'heure et les minutes
        $hour       = $splitedHour[0];
        $minutes    = $splitedHour[1];
        
        //conversion de l'heure, moins les 8 premieres qui ne comptent pas
        $hourByFive 	= (intval($hour)*12)-(8*12);
        //conversion des minutes
        $minutesByFive 	= intval($this->byFive($minutes));

        //le temps total par tranche de 5 minutes
        $time = $hourByFive + $minutesByFive;

        return $time;
    }
    
    function createBlock($endBlock, $beginBlock, $blockName, $wcol, $hcol, $fill)
    {
    	if ($fill)
    	{
    		$text = $blockName.'('.$beginBlock.'-'.$endBlock.')';
    	}
    	else
    	{
    		$text = $blockName;
    	}
    	
    	$endOfBlock 		= $this->splitHour($endBlock);
    	$beginingOfBlock 	= $this->splitHour($beginBlock);
    	
    	$blockDuration 		= $endOfBlock - $beginingOfBlock;
    	
    	$hcell 				= $hcol * $blockDuration;
    	
    	$this->Cell($wcol, $hcell, $text, 'LRBT', 2, 'C', $fill);
    }
    
    function createDay($data, $wcol, $hcol, $header)
    {
    	for ($p = 0; $p < count($data); $p++)
    	{
    		if ($p == 0 and $p == count($data)-1)
    		{
    			$this->checkFreeSpace	("8:00", $data[$p][1], $wcol, $hcol);
    			$this->createBlock		($data[$p][2], $data[$p][1], $data[$p][0], $wcol, $hcol, true);
    			$this->checkFreeSpace	($data[$p][2], "20:00", $wcol, $hcol);
    		}
    		else if ($p == 0 and $p <> count($data)-1)
    		{
    			$this->checkFreeSpace	("8:00", $data[$p][1], $wcol, $hcol);
    			$this->createBlock		($data[$p][2], $data[$p][1], $data[$p][0], $wcol, $hcol, true);
    		}
    		else if ($p == count($data)-1 and $p <> 0)
    		{
    			$this->checkFreeSpace	($data[$p-1][2], $data[$p][1], $wcol, $hcol);
    			$this->createBlock		($data[$p][2], $data[$p][1], $data[$p][0], $wcol, $hcol, true);
    			$this->checkFreeSpace	($data[$p][2], "20:00", $wcol, $hcol);
    		}
    		else
    		{
    			$this->checkFreeSpace	($data[$p-1][2], $data[$p][1], $wcol, $hcol);
    			$this->createBlock		($data[$p][2], $data[$p][1], $data[$p][0], $wcol, $hcol, true);
    		}
    	}
    }

    // Tableau colore
    function FancyTable($header, $data)
    {
    	$lineWidth = 0.3;
    	
        // Couleurs, Epaisseur du trait et police grasse
        $this->SetFillColor(255, 0, 0);
        $this->SetTextColor(255);
        $this->SetDrawColor(0, 0, 0);
        $this->SetLineWidth($lineWidth);
        $this->SetFont('', 'B');
        
        $hheader 		= 5;
        $dayDuration 	= 12*12;
        $margin  		= 20;
        $positionheader = $this->GetY();
        
        //taille d'une cellule standard (5 min)
        $wcol = (($this->GetPageWidth())-$margin-($lineWidth*8))/count($header);
        $hcol = (($this->GetPageHeight())-(2*$margin)-$hheader-($lineWidth*3))/$dayDuration;
        
        // En-tete
        for($i = 0; $i < count($header); $i++)
        {   
            $tabPositionX[$i] = $this->GetX();
            $this->Cell($wcol, $hheader, $header[$i], 1, 0, 'C', true);
        }
        $this->Ln();

        // Restauration des couleurs et de la police
        $this->SetFillColor(200,255,200);
        $this->SetTextColor(0);
        $this->SetFont('');
        
        //Donnees
        $tabData = [];
        
        for ($j = 0; $j < count($header); $j++)
        {
        	$m = 0;
        	for ($k = 0; $k < count($data); $k++)
        	{
        		if ($data[$k][3] == $j)
        		{
        			$tabData[$j][$m] = $data[$k];
        			$m++;
        		}
        	}
        }
        
        for ($iterator = 0; $iterator < count($header); $iterator++)
        {
        	$this->SetXY($tabPositionX[$iterator], $positionheader + $hheader);
        	$this->createDay($tabData[$iterator], $wcol, $hcol, $header);
        }
    }
}

$pdf = new PDF();

// Titres des colonnes
$header = array('Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche');

// Chargement des donnees
$data = $pdf->LoadData('test.txt');

$pdf->SetFont('Arial', '', 9);

$pdf->AddPage('L');
$pdf->FancyTable($header, $data);

$pdf->Output();
?>
