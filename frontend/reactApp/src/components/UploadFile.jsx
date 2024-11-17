import React, {useCallback, useState} from 'react'
import {useDropzone} from 'react-dropzone'
import {errorNotification, successNotification} from "../services/notification.js";
import { updateClientFile} from "../services/clientFile.js";
import {Box,Text,Image} from "@chakra-ui/react";

function UploadFile({client}) {
    const[submitting,setSubmitting] = useState(false);

    const onDrop = useCallback((acceptedFiles) => {
        acceptedFiles.forEach((file) => {
            const reader = new FileReader();

            reader.onabort = () => console.log('file reading was aborted');
            reader.onerror = () => console.log('file reading has failed');
            reader.onload = () => {
                const fileData = {
                    fileContent: reader.result,
                    fileName: file.name,
                    fileSize: file.size,
                    fileFormat: file.type,
                    clientId: client.id
                }

                updateClientFile(
                    client.id,
                    fileData
                ).then(res => {
                    successNotification(
                        "File saved",
                        `${client.lastName} ${client.firstName} file was successfully saved`
                    )
                }).catch(err => {
                    console.log(err);
                    errorNotification(
                        err.code,
                        err.response.message
                    );
                });
            }
            reader.readAsDataURL(file);

        });
        }, []);


    const {
        getRootProps,
        getInputProps
    } = useDropzone({onDrop,
        accept: {
            'image/png': ['.png'],
            'image/jpeg': ['.jpg','.jpeg'],
            'application/pdf': ['.pdf'],
        }
    });

    return (
        <section className="container">
            <Box
                {...getRootProps({className: 'dropzone'})}
                w={'100%'}
                textAlign={'center'}
                border={'dashed'}
                borderColor={'gray.200'}
                borderRadius={'3xl'}
                p={6}
                rounded={'md'}

            >
                <input {...getInputProps()} />
                <p>Drag 'n' drop some files here, or click to select files</p>
                <em>(Only *.jpeg and *.png and *.pdf)</em>
            </Box>
        </section>
    );
}

export default UploadFile;