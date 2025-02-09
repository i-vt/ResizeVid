# ResizeVid
ResizeVid is a simple WebUI using SpringBoot
![image](https://github.com/user-attachments/assets/7aabbd3c-5e03-4bc0-bbb0-20ef6c6280a8)

## Installation:
### Prereqs
- FFMPEG
- JDK 23
### Running it
`./gradle bootRun`
### WebUI
`http://localhost:8080/`

## InnerWorkings
### API POST
```
curl 'http://localhost:8080/upload' -X POST -H 'User-Agent: ibechilling' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/png,image/svg+xml,*/*;q=0.8' -H 'Accept-Language: en-US,en;q=0.5' -H 'Accept-Encoding: gzip, deflate, br, zstd' -H 'Content-Type: multipart/form-data; boundary=---------------------------42404143143876195079750815176' -H 'Origin: http://localhost:8080' -H 'Connection: keep-alive' -H 'Referer: http://localhost:8080/' -H 'Cookie: JSESSIONID=5412300CC1FEA11738F6D078DBAEC7DB' -H 'Upgrade-Insecure-Requests: 1' -H 'Sec-Fetch-Dest: document' -H 'Sec-Fetch-Mode: navigate' -H 'Sec-Fetch-Site: same-origin' -H 'Sec-Fetch-User: ?1' -H 'Priority: u=0, i' --data-binary $'-----------------------------42404143143876195079750815176\r\nContent-Disposition: form-data; name="file"; filename="6df5cb2d37284b828fe872b2459e8c69.mov"\r\nContent-Type: video/quicktime\r\n\r\n-----------------------------42404143143876195079750815176--\r\n'
```
### FFMPEG backend
`ffmpeg -i 0a0cd6d5-525a-4f96-beb5-32a2536a7c71.mov -vf "scale=-2:360" resized-0a0cd6d5-525a-4f96-beb5-32a2536a7c71.mov`

### Request
```
curl 'http://localhost:8080/videos/resized-0a0cd6d5-525a-4f96-beb5-32a2536a7c71.mov' -H 'User-Agent: ibechilling' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/png,image/svg+xml,*/*;q=0.8' -H 'Accept-Language: en-US,en;q=0.5' -H 'Accept-Encoding: gzip, deflate, br, zstd' -H 'Connection: keep-alive' -H 'Referer: http://localhost:8080/' -H 'Cookie: JSESSIONID=A853E843D58FEED29C78C38BE0A10FE7' -H 'Upgrade-Insecure-Requests: 1' -H 'Sec-Fetch-Dest: document' -H 'Sec-Fetch-Mode: navigate' -H 'Sec-Fetch-Site: same-origin' -H 'Sec-Fetch-User: ?1' -H 'Priority: u=0, i'
```


## Disclaimer for "ResizeVid":

### Summary: 
Don't be an idiot and be responsible with usage. Pentesting without authorization is illegal.

### In depth: 
1. General Use: This software is provided "as is", without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose, and non-infringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.
2. Potential Misuse: The software is designed for legitimate purposes only. Any misuse, including but not limited to illegal, unethical, or unauthorized activities, is strictly discouraged and not the intention of the developers.
3. User Responsibility: Any person, entity, or organization choosing to use this software bears the full responsibility for its actions while using the software. It is the user's responsibility to ensure that their use of this software complies with local, state, national, and international laws and regulations.
4. No Liability: The creators, developers, and distributors of this software are not responsible for any harm or damage caused, directly or indirectly, by the misuse or use of this software.
5. Updates and Monitoring: The developers reserve the right to update, modify, or discontinue the software at any time. Users are advised to always use the most recent version of the software. However, even with updates, the developers cannot guarantee that the software is completely secure or free from vulnerabilities.
6. Third-Party Software/Links: This software may contain links to third-party sites or utilize third-party software/tools. The developers are not responsible for the content or privacy practices of those sites or software.
7. Unauthorized Access: Using "ResizeVid" to access, probe, or connect to systems, networks, or data without explicit permission from appropriate parties is strictly discouraged, unethical, and illegal. Unauthorized access to systems, networks, or data breaches various local, national, and international laws, and can result in severe legal consequences. Always obtain the necessary permissions before accessing any systems or data. The developers of "ResizeVid" disavow any actions taken by individuals or entities that use this software for unauthorized activities.

By downloading, installing, or using "ResizeVid" you acknowledge that you have read, understood, and agreed to abide by this disclaimer. If you do not agree to these terms, do not use the software.
