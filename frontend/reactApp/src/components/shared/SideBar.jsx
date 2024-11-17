import React from 'react';
import {
    Avatar,
    Box,
    CloseButton,
    Drawer,
    DrawerContent,
    Flex,
    HStack,
    Icon,
    IconButton,
    Link,
    Menu,
    MenuButton,
    MenuDivider,
    MenuItem,
    MenuList,
    Text,
    useColorModeValue,
    useDisclosure,
    VStack,
    Image, MenuGroup, Button
} from '@chakra-ui/react';

import {
    FiBell,
    FiChevronDown,
    FiHome,
    FiMenu,
    FiSettings,

} from 'react-icons/fi';
import CreateClientDrawer from "../CreateClientButton.jsx";

const LinkItems = [
    {name: 'Home', icon: FiHome},
    {name: 'Settings', icon: FiSettings},
];

export default function Sidebar() {
    const {isOpen, onOpen, onClose} = useDisclosure();


    return (
                <SidebarContent
                    onClose={() => onClose}
                    display={{base: 'none', md: 'block'}}
                    flexShrink={0}
                    w={{ md: '60' }}
                />

    );
}

const SidebarContent = ({onClose, ...rest}) => {
    return (
        <Box
            transition="3s ease"
            bg={useColorModeValue('white', 'gray.900')}
            borderRight="1px"
            borderRightColor={useColorModeValue('gray.200', 'gray.700')}
            w={{base: 'full', md: 20}}
            pos="fixed"
            left={0}
            h="full"
            {...rest}>
            <Flex h="20" flexDirection="column" alignItems="center" mx="8" mb={75} mt={2} justifyContent="space-between">
                <Text fontSize="2xl" fontFamily="monospace" fontWeight="bold" mb={5}>
                    Dashboard
                </Text>
                <Image
                    borderRadius='full'
                    boxSize='75px'
                    src='/src/burgerkingguy copy.gif'
                    alt='Amigoscode'
                />
                <CloseButton display={{base: 'flex', md: 'none'}} onClick={onClose}/>
            </Flex>
            {LinkItems.map((link) => (
                <NavItem key={link.name} icon={link.icon}>
                    {link.name}
                </NavItem>
            ))}
        </Box>
    );
};

const NavItem = ({icon, children, ...rest}) => {
    return (
        <Link href="frontend/react/src/components/shared#" style={{textDecoration: 'none'}} _focus={{boxShadow: 'none'}}>
            <Flex
                align="center"
                p="4"
                mx="4"
                borderRadius="lg"
                role="group"
                cursor="pointer"
                _hover={{
                    bg: 'cyan.400',
                    color: 'white',
                }}
                {...rest}>
                {icon && (
                    <Icon
                        mr="4"
                        fontSize="16"
                        _groupHover={{
                            color: 'white',
                        }}
                        as={icon}
                    />
                )}
                {children}
            </Flex>
        </Link>
    );
};

const ProfileNav = ()=>{
    return(
        <Menu style={{ position: 'fixed', top: 0, right: 0 }}>
            <MenuButton as={Button} colorScheme='pink'>
                Profile
            </MenuButton>
            <MenuList>
                <MenuGroup title='Profile'>
                    <MenuItem>My Account</MenuItem>
                    <MenuItem>Payments </MenuItem>
                </MenuGroup>
                <MenuDivider />
                <MenuGroup title='Help'>
                    <MenuItem>Docs</MenuItem>
                    <MenuItem>FAQ</MenuItem>
                </MenuGroup>
            </MenuList>
        </Menu>
    )
}