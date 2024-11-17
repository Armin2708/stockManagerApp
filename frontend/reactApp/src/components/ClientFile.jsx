import {Box, Button, Card, CardBody, CardFooter, CardHeader, Flex, Heading, Text} from "@chakra-ui/react";
import {DownloadIcon} from "@chakra-ui/icons";
import {useCallback, useState} from "react";
import {getClientFile, updateClientFile} from "../services/clientFile.js";
import {errorNotification, successNotification} from "../services/notification.js";
import { Document, Page } from 'react-pdf';
export default function ClientFile({file}){

    const fetchFile = useCallback((id) => {
        return getClientFile(id)
            .then(res => {
                successNotification(
                    "File fetched"
                )
                return res.data;
            }).catch(err => {
                console.log(err);
                errorNotification(
                    err.code,
                    err.response.message
                );
            });
        }, []);

    const base64StringToFile = (base64, format) => {

        const byteCharacters = atob(base64);
        const byteNumbers = new Array(byteCharacters.length);

        for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
        }

        const byteArray = new Uint8Array(byteNumbers);

        return new Blob([byteArray], {type: format});
    }

    const handleDownload = async(id) =>{
        const fileData = await fetchFile(id);
        const downloadLink = document.createElement('a');
        downloadLink.href = URL.createObjectURL(base64StringToFile(fileData.fileContent,fileData.fileFormat));
        downloadLink.setAttribute('download', fileData.fileName);
        downloadLink.click();
    }

    let fileSizeInMb = file.fileSize / (1024 * 1024);
    let formattedFileSize = fileSizeInMb.toFixed(2) + " Mb";
    return(
        <Box maxW='sm' borderWidth='1px' borderRadius='lg' overflow='hidden'>
            <Flex justifyContent={"center"} alignItems="center">
                <Text
                    mt='1'
                    fontWeight='semibold'
                    as='h4'
                    lineHeight='tight'
                    noOfLines={1}
                >
                    {file.fileName}
                </Text>
            </Flex>
            <Flex justifyContent={"center"} alignItems="center">
                <Button size={'sm'} onClick={()=> handleDownload(file.id)}><DownloadIcon/>{formattedFileSize}</Button>
            </Flex>
        </Box>
    )
}